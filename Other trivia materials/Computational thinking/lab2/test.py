def bSearch(array, target):
    lower = -1
    upper = len(array)
    
    while not (lower + 1 == upper): #fail if region empty
        mid = (lower + upper)//2 #find middle of region
        if target == array[mid]: #succeed if k is at mid
            return mid
        elif target < array[mid]:
            upper = mid #search lower region
        else:
            lower = mid #search upper region
    return -1

def search(arr,target):

    low = 0
    high = len(arr)-1

    startindex = -1
    while (low <= high):
        mid = (high-low)//2 + low
        if(arr[mid] > target):
            high = mid -1
        elif arr[mid] == target:
            startindex = mid
            high = mid-1
        else:
            low = mid +1
    endindex = -1
    low = 0;
    high = len(arr) -1
    while (low <= high):
        mid = (high-low)//2 + low
        if(arr[mid]  > target):
            high = mid -1
        elif arr[mid] == target:
            endindex = mid
            low = mid+1
        else:
            low = mid + 1

    return endindex , startindex
a = [1,2,3,4,5,5,5,5,5,5,5,6,7,8,9]
b = [1,2,3,4,5,6,7,8,9]
print(search(a,10))

