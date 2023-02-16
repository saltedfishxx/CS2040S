# Name: <TODO: replace with your name>
# Section: <TODO: replace with your section>

# lab2b

# All statements should only be in functions. Do not include statements outside functions in this file.

# INSTRUCTIONS: 
# Refer to the code in lab2b_main.py - perform_once will be called one time before 
# exist is called many times. You may modify perform_once if desired, or keep it as it is.
def perform_once(employee_with_birthyear_list):
  # Write any code here if desired. Any code you do here will replace the original employee_list
  employee_with_birthyear_list.sort(key=lambda x: x[1])
  return employee_with_birthyear_list

  
# INSTRUCTIONS: 
# Write a function called get_IDs_with_birthyear that takes in a year (as an integer) and an array (employee_with_birthyear_list)
# It then returns an array of employee IDs (integers) that have matching birthyears.
# If there is no match, this function returns an empty array (i.e. []).
def get_IDs_with_birthyear(year, employee_with_birthyear_list):
  # for now, this function always returns an empty list
  result = []
  low = 0
  high = len(employee_with_birthyear_list)-1

  startindex = -1
  while (low <= high):
      mid = (high-low)//2 + low
      if(employee_with_birthyear_list[mid][1] > year):
          high = mid -1
      elif employee_with_birthyear_list[mid][1] == year:
          startindex = mid
          high = mid-1
      else:
          low = mid +1
  endindex = -1
  low = 0;
  high = len(employee_with_birthyear_list) -1
  while (low <= high):
      mid = (high-low)//2 + low
      if(employee_with_birthyear_list[mid][1]  > year):
          high = mid -1
      elif employee_with_birthyear_list[mid][1] == year:
          endindex = mid
          low = mid+1
      else:
          low = mid + 1
  if(endindex == -1 and startindex == -1):
    return []
  else:
    
    for i in range(startindex,endindex+1):
      result.append(employee_with_birthyear_list[i][0])

  return result


