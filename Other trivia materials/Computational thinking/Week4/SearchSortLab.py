def LinearSearch(array, target):
    '''Linear Search with For Loop'''
    for i in range(0, len(array)):
        if array[i] == target:
            return i
    return -1

def lSearch(array, target):
    '''Linear Search with While Loop'''
    i = 0
    while i < len(array):
        if array[i] == target:
            return i
        i += 1
    return -1

def bSearch(array,target):
    lower = -1
    upper = len(array)
    while not (lower + 1 == upper): #fail if region empty
        mid = (lower+upper)//2 #find middle of region
        if target == array[mid]: #succeed if k is at mid
            return mid
        elif target < array[mid]: 
            upper = mid #search lower region
        else:
            lower = mid #search upper region
    return -1

def iSort(array):
    i = 1
    while i < len(array):
        move_left(array,i)
        i += 1
    return array
    
    
def move_left(array,i):
    while i > 0 and array[i] < array[i-1]:
        array[i],array[i-1] = array[i-1], array[i] # swap elements at i-1 and i
        i -= 1 # move left

def mSort(array):
    groupsize = 1 #start from groups of size 1
    while groupsize < len(array):
        mergeGroups(array, groupsize) #merge pairs of groups
        groupsize *= 2 #double the size of the group
    return array

def mergeGroups(array, groupsize):
    i = 0
    while i < len(array):
        j = i + 2 * groupsize
        array[i:j] = merge(array, i, groupsize)
        i = i + (2 * groupsize)

def merge(array, i ,groupsize):
    resultArray = [] #new array to store merged result
    firstGroup = array[i:i+groupsize] #end of first group
    secondGroup = array[i+groupsize:i+groupsize*2] #end of second group
    while (len(firstGroup) != 0 or len(secondGroup) != 0):
        if(len(firstGroup) == 0):
            while (len(secondGroup) != 0):
                resultArray.append(secondGroup.pop(0))
        elif (len(secondGroup) == 0):
            while (len(firstGroup) != 0):
                resultArray.append(firstGroup.pop(0))
        else:
            if(firstGroup[0] > secondGroup[0]):
                resultArray.append(secondGroup.pop(0))
            else:
                resultArray.append(firstGroup.pop(0))
    return resultArray
        
def rmSort(a):
    if len(a) == 1:
        return a
    mid = len(a)//2
    a1 = rmSort(a[0:mid])
    a2 = rmSort(a[mid:len(a)])  
    return merge2(a1, a2)

def merge2(a1, a2):
    i = 0
    j = 0
    ret = []
    while i < len(a1) or j < len(a2):
        if (j == len(a2)) or (i < len(a1) and a1[i] < a2[j]):
            ret.append(a1[i]) # pick item from 1st group
            i += 1
        else:
            ret.append(a2[j]) # pick item from 2nd group
            j += 1
    return ret
    
    
def qSort(array, low = None, high = None):
    if low == None:
        low = 0
    if high == None:
        high = len(array)-1
    if low < high:
        p = pivot(array, low, high)
        qSort(array, low, p-1)
        qSort(array, p+1, high)
    return array

def pivot(array, low, high):
    i = low
    currPivot = array[high]
    for j in range(low, high):
        if array[j] < currPivot:
            array[i],array[j] = array[j],array[i]
            i += 1
    array[i],array[high] = array[high],array[i]
    return i
