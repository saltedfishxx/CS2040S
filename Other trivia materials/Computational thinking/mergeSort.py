def mSort(array):
  groupsize = 1
  while groupsize < len(array):
    merge_groups(array,groupsize)
    groupsize *= 2
  return array

def merge_groups(a,gs):
  i = 0
  while i < len(a):
    j = i + 2*gs
    a[i:j] = merge(a,i,gs)
    i += 2*gs

def merge(array,i,groupsize):
  r = []
  firstGroup = array[i:i+groupsize]
  secondGroup = array[i+groupsize:i+groupsize*2]

  while (len(firstGroup) != 0 or len(secondGroup) != 0):
    if(len(firstGroup) == 0):
      while (len(secondGroup) != 0):
        r.append(secondGroup.pop(0))
    elif (len(secondGroup) == 0):
      while (len(firstGroup) != 0):
        r.append(firstGroup.pop(0))
    else:
      if(firstGroup[0] > secondGroup[0]):
        r.append(secondGroup.pop(0))
      else:
        r.append(firstGroup.pop(0))
    
  return r

  left = 0
  right = (len(employee_list)-1)

  while(left <= right):
    mid = left +(int((right-left)/2))
    if(employee_list[mid] == id):
      return True
    elif id < employee_list[mid]:
      right = mid - 1
    else:
      left = mid + 1
                     
      
  return False  # not found
