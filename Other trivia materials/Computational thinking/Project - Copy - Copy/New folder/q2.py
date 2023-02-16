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

from q1 import *

def select_packageSets(n, W, packages):
  # print(n)
  # print(W)
  # print(packages)

  # 1. Sort value weight ratio. Descending order
  # 2. Give highest ratio to member 1... until all member get 1 package
  # 3. Give the next best remaining package from the back

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

  output = []
  for aPerson in result_list:
    # print(aPerson)
    output += [select_packageSet(W, aPerson)]

  # print(output)

  # TODO: edit this function's body
  # return [[P001, P003], [P011, P007], [P004, P005, P006], [P012]]
  # return [[] for _ in range(n)]
  return output
