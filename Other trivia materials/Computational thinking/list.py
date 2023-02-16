def list():
    a = [1,2,3,4]
    print(a)

    name = 'test'
    len(name)
    len(a)

    a,append(88)
    print(a)
    len(a)

    b = []
    len(b)
    b.append("orange")
    print(b)
    len(b)

def list2d():
    firstRow = [3,5,'orange']# 3 elements 
    secRow = [7,8,False] # 3 elements
    twoDlist = [firstRow,secRow] # 2 element 
    print(firstRow)
    print(secRow)
    print(twoDlist)

numberslist = [1,2,3,4]
stringlist = ["a","b","c","d"]
def print_list(a):
    counter = 0
    for x in a : # for every value inside a
        counter += 1 #add one to the variable counter
        print(counter, end=".") # print as 1. without going to next line
        print("Value of list",x) # do the following

def total(a):
    sum = 0
    for x in a:
        sum += x 
    return sum

#Note: items inside a list the index always start with 0 e.g. Sample[0]
# The last index of a list is n-1


def altlist():
    a = ['iii','333','33333'] #declaring a list
    print(a)
    
    a[0] = "Orange" #assign new value to list a, index 0 from iii to orange
    print(a)
    
    a.append("apple") # add new element into list a from the back
    print(a)
    
    a.insert(0,"Peach")# insert new element from select position 0
    print(a)
    
    a.insert(2,"grape")# insert new element from select position 2
    print(a)

    firstRow = [3,5,'orange']# 3 elements 
    secRow = [7,8,False] # 3 elements
    twoDlist = [firstRow,secRow] # 2 element
    print(twoDlist[0]) #print the first element in twoDlist

    print(twoDlist[0][0]) #print the first element in firstRow

    print(twoDlist[0][-1])


    
def partial_total(sR,eR,a):
    sum = 0
    for i in range(sR,eR):#for every number in range of sR and eR
        sum += a[i]
    return sum
    
    

    
