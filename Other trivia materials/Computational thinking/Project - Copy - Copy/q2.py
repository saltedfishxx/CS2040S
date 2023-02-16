# <Your Team ID>
# <Team members' names>

# Q2

# Replace the content of this function with your own algorithm
# inputs: 
#   n: the number of members in your team
#   W: weight limit of each vehicle used for deliveries.
#   packages: 2D list [[packageID, reward, weight], [packageID, reward, weight], ...]
# returns:
#   2D list of package IDs to represent n sets of packages. 
#   e.g. if n = 2, this is a possible solution: [["P001", "P003"], ["P010"]]
def select_packageSets(n, W, packages):
  # print(n)
  # print(W)
  # print(packages)

  # 1. Sort value weight ratio. Descending order
  # 2. Give highest ratio to member 1... until all member get 1 package
  # 3. Give the next best remaining package from the back
  verify_output = {}
  tuple_packages = tuple(packages)
  for pack in packages:
    
    verify_output[pack[0]] = [pack[1],pack[2]]
    
    
  output1 = solve_v1(n, W, list(tuple_packages))
  output2 = solve_v2(n, W, list(tuple_packages))
    
  output1_reward = []
  output2_reward = []
  
  temp_reward = 0
  for item_set in output1:
    for item in item_set:
      temp_reward += verify_output[item][0]
    output1_reward.append(temp_reward)
    temp_reward = 0
    
  temp_reward = 0
  for item_set in output2:
    for item in item_set:
      temp_reward += verify_output[item][0]
    output2_reward.append(temp_reward)
    temp_reward = 0
    
      
  print(output1_reward)
  print(output2_reward)
  
  if min(output1_reward) >= min(output2_reward):
    return output1
  else:
    return output2
 
  
  # print(output)

  # TODO: edit this function's body
  # return [[P001, P003], [P011, P007], [P004, P005, P006], [P012]]
  # return [[] for _ in range(n)]
  
#Method 1 solving ---------------------------------------------------------------------------------------
def solve_v1(n, W, packages):
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

#Method 2 solving --------------------------------------------------------------------------------
def solve_v2(n, W, packages):
  new_package_list = []

  #sorting the package
  for package in packages:
    value_weight = package[1] / package[2]
    new_package_list.append([package[0], package[1], package[2], value_weight])

  new_package_list.sort(key=lambda x: -x[3])

  # creating the result list
  result_list = []
  for i in range(n):
    result_list += [[]]

  while len(new_package_list) != 0:
    for i in range(n):
      if i > len(new_package_list)-1:
        continue
      else:
        result_list[i] += [new_package_list[i][:-1]]
    new_package_list = new_package_list[n:]
    new_package_list = new_package_list[::-1]
    #print(len(new_package_list))

  output2 = []
  for aPerson in result_list:
    # print(aPerson)
    output2 += [select_packageSet(W, aPerson)]
  return output2

#Q1 code -----------------------------------------------------------------------------------
def select_packageSet(W, packages):
  
  #print(knapSack(W,packages,len(packages)))
  #print(unbounded_knapsack(packages,W))
  new_p = []
  output = []
  for p in sorted(packages,key=lambda l:l[2]):
    if p[2] <= W:
      new_p.append(tuple(p))
  
  sol = solve(tuple(new_p),W)
  for s in sol:
    output.append(s[0])
  return output
     
 
def total_value(items, max_weight):
    return  sum([x[1] for x in items]) if sum([x[2] for x in items]) <= max_weight else 0
  
def solve(items, max_weight):
    if not items:
        return ()
    if (items,max_weight) not in cache:
        head = items[0]
        tail = items[1:]
        include = (head,) + solve(tail, max_weight - head[2])
        dont_include = solve(tail, max_weight)
        if total_value(include, max_weight) > total_value(dont_include, max_weight):
            answer = include
        else:
            answer = dont_include
        cache[(items,max_weight)] = answer
    return cache[(items,max_weight)]
 

cache = {}
result = []

