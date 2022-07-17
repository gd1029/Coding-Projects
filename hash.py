import hashlib
import csv
lines = csv.reader(open('./one.txt'))

for line in lines:
    a = hashlib.md5()
    a.update(f'tran:{line[0]}'.encode('utf-8'))
    if a.hexdigest() == 'aca15b0528e97c861e86e091f6104e1e':
        print(line)
