import hashlib


i = 0

ha1_lst = open("ha12.txt", "r")
ha1_lines = ha1_lst.readlines()
length = len(ha1_lines)

count = 0
nounce = "03e2abb8a924e966bee59d41cef32851"
ha2 = "GET:/Public/CS/Home.png"
ha2 = hashlib.md5(ha2.encode())
ha2 = ha2.hexdigest()
ha2 = str(ha2)
new = nounce+":"+ha2

ha1="sharell:Mordor:"

for i in range(length):
    each = ha1_lines[i].strip()
    each = hashlib.md5(each.encode())
    each = each.hexdigest()
    final = each+":"+new
    hashnum = hashlib.md5(final.encode())
    hexnum = hashnum.hexdigest()
    if (hexnum == "3fec5252001d223be9ed2fd3baaa9f6d"):
        print(ha1_lines[i])
        break
    else:
        i +=1
        ha1="sharell:Mordor:"
        print("null")

    

    
    
    


