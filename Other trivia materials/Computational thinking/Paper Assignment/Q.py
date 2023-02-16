def binary_min(arr):
    if len(arr) == 1:
        return arr[0]
    else:
        mid = len(arr)//2
        min1 = binary_min(arr[0:mid])
        min2 = binary_min(arr[mid:len(arr)])
        if min1 < min2:
            print("A")
            return min1
        else:
            print("B")
            return min2
arr = [1,2,3,4,5,6,7,123]
print(binary_min(arr))
