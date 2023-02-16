def transfrom(str1,str2):
    s1 =[]
    s2 =[]
    result = [str1]
    for ch in str1: #convert str1 into a list 
        s1.append(ch)
    for ch in str2: #convert str2 into a list 
        s2.append(ch)

    for i in range(len(s1)):
        if s1[i] != s2[i]: #s1 character i not = to s2 character i 
            temp_str = ""
            position = s1.index(s2[i]) #find the location of s2 in s1 
            for p in range(position,i,-1):# stop swaping until the current position
                s1[p],s1[p-1]=s1[p-1],s1[p] #swaping characters 
                for ch in s1:
                    temp_str += ch                     
                position -= 1
                result.append(temp_str) #add updated string to result 
                temp_str = ""#reset temporary string 
    return result
print(transfrom("ABCDE","DBECA"))
