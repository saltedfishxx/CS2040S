def select_packageSetsVer2(n, W, packages):
  # TODO: edit this function's body
  for p in packages:
    weight_ratio = p[1]/p[2]                      #get weight ratio
    p.append(weight_ratio)                        #add new col to each package

  packages.sort(key=lambda x: -x[3])              #sort the packages according weight ratio in desc order
  
  assignment_of_pack = []
  
  for i in range(n):                              #get first item for each member 
    assignment_of_pack.append([packages[i]])      #add package to member
    packages.remove(packages[i])                  #remove item from packages to prevent duplicate
    
  packages.sort(key=lambda x: -x[3],reverse=True) 
  
  for pack in assignment_of_pack:                 #for each output
    weight_left = W - pack[0][2]                  #find weight left
    for i in range(len(packages)-1,-1,-1):         #loop the remaining packages
      if weight_left >= packages[i][2]:           #if item can be put in
        pack.append(packages[i])                  #add package to the member
        weight_left = weight_left-packages[i][2]  #new weight left
        packages.remove(packages[i])              #remove item
  
  result = []
  for member in assignment_of_pack:               #loop through the assignment_of_pack
    temp = []
    for item in member:
      temp.append(item[0])                        #add item id to result
    result.append(temp)
      
  return result
