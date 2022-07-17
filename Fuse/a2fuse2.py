#Name: Grace Du
#Login: mdu119

#!/usr/bin/env python
from __future__ import print_function, absolute_import, division

import logging
import os
import sys
import ctypes

from collections import defaultdict
from errno import ENOENT
from stat import S_IFDIR, S_IFLNK, S_IFREG
from sys import argv, exit
from time import time

from fuse import FUSE, FuseOSError, Operations, LoggingMixIn
from passthrough import Passthrough

if not hasattr(__builtins__, 'bytes'):
    bytes = str  

class A2Fuse2(LoggingMixIn, Operations):
    'Example memory filesystem. Supports only one level of files.'

    def __init__(self, roots):
        self.log = logging.getLogger('grace du: fuse.log-mixin')
        #local
        self.lfiles = {}
        for root in roots:
            st = os.lstat(root)
            self.lfiles[root] = dict((key, getattr(st, key)) for key in ('st_atime', 'st_ctime',
                        'st_gid', 'st_mode', 'st_mtime', 'st_nlink', 'st_size', 'st_uid'))
        #memory
        self.files = {}
        self.data = defaultdict(bytes)
        self.fd = 0
        now = time()
        self.files['/'] = dict(st_mode=(S_IFDIR | 0o755), st_ctime=now,
                               st_mtime=now, st_atime=now, st_nlink=2)

    def _full_path(self, partial):
        #local
        if partial.startswith("/"):
            partial = partial[1:]

        for root in self.lfiles:
            path = os.path.join(root, partial)
            if os.path.exists(path):
                break
        return path

    def chmod(self, path, mode):
        self.files[path]['st_mode'] &= 0o770000
        self.files[path]['st_mode'] |= mode
        return 0

    def chown(self, path, uid, gid):
        self.files[path]['st_uid'] = uid
        self.files[path]['st_gid'] = gid

    def create(self, path, mode):
        self.files[path] = dict(st_mode=(S_IFREG | mode), st_nlink=1,
                                st_size=0, st_ctime=time(), st_mtime=time(),
                                st_atime=time(),st_uid = 502, st_gid = 20)
        self.fd += 1
        return self.fd

    def getattr(self, path, fh=None):
        if path not in self.files:
            full_path = self._full_path(path)
            st = os.lstat(full_path)
            return dict((key, getattr(st, key)) for key in ('st_atime', 'st_ctime',
                        'st_gid', 'st_mode', 'st_mtime', 'st_nlink', 'st_size', 'st_uid'))
        else:
            return self.files[path]

    def getxattr(self, path, name, position=0):
        attrs = self.files[path].get('attrs', {})

        try:
            return attrs[name]
        except KeyError:
            return ''       # Should return ENOATTR

    def listxattr(self, path):
        attrs = self.files[path].get('attrs', {})
        return attrs.keys()

    def mkdir(self, path, mode):
        self.files[path] = dict(st_mode=(S_IFDIR | mode), st_nlink=2,
                                st_size=0, st_ctime=time(), st_mtime=time(),
                                st_atime=time())
        self.files['/']['st_nlink'] += 1

    def open(self, path, flags):
        if path not in self.files:
            full_path = self._full_path(path)
            return os.open(full_path, flags)
        else:
            self.fd += 1
            return self.fd

    def read(self, path, size, offset, fh):
        if path not in self.files:
            os.lseek(fh, offset, os.SEEK_SET)
            return os.read(fh, size)
        else:
            return self.data[path][offset:offset + size]

    def readdir(self, path, fh):
        #memory
        dirents = ['.', '..'] + [x[1:] for x in self.files if x != '/']
        
        #local
        for root in self.lfiles:
            if path.startswith("/"):
                path = path[1:]
            full_path = os.path.join(root, path)
            if os.path.isdir(full_path):
                dirents.extend(os.listdir(full_path))
        
        for r in dirents:
            yield r

    def readlink(self, path):
        return self.data[path]

    def removexattr(self, path, name):
        attrs = self.files[path].get('attrs', {})

        try:
            del attrs[name]
        except KeyError:
            pass        # Should return ENOATTR

    def rename(self, old, new):
        self.files[new] = self.files.pop(old)

    def rmdir(self, path):
        self.files.pop(path)
        self.files['/']['st_nlink'] -= 1

    def setxattr(self, path, name, value, options, position=0):
        # Ignore options
        attrs = self.files[path].setdefault('attrs', {})
        attrs[name] = value

    def statfs(self, path):
        return dict(f_bsize=512, f_blocks=4096, f_bavail=2048)

    def symlink(self, target, source):
        self.files[target] = dict(st_mode=(S_IFLNK | 0o777), st_nlink=1,
                                  st_size=len(source))
        self.data[target] = source

    def truncate(self, path, length, fh=None):
        if path not in self.files:
            full_path = self._full_path(path)
            with open(full_path, 'r+') as f:
                f.truncate(length)
        else:
            self.data[path] = self.data[path][:length]
            self.files[path]['st_size'] = length

    def unlink(self, path):
        if path not in self.files:
            return os.unlink(self._full_path(path))
        else:
            self.files.pop(path)

    def utimens(self, path, times=None):
        now = time()
        atime, mtime = times if times else (now, now)
        self.files[path]['st_atime'] = atime
        self.files[path]['st_mtime'] = mtime

    #def write(self, path, data, offset, fh):
    def write(self, path, buf, offset, fh):
        if path not in self.files:
            #local
            os.lseek(fh, offset, os.SEEK_SET)
            return os.write(fh, buf)
        else:
            #memory
            data = ctypes.string_at(buf)
            self.data[path] = self.data[path][:offset] + data
            self.files[path]['st_size'] = len(self.data[path])
            return len(data)

def main(mountpoint, root2, root1):
    roots = []
    roots.append(root1)
    roots.append(root2)
    FUSE(A2Fuse2(roots), mountpoint, nothreads=True, foreground=True)

if __name__ == '__main__':
    logging.basicConfig(level=logging.DEBUG)
    main(sys.argv[3], sys.argv[2], sys.argv[1])
