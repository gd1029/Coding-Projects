import hashlib

ha1 = hashlib.md5("helloworld:235623605".encode())
ha1 = ha1.hexdigest()
print(ha1)
