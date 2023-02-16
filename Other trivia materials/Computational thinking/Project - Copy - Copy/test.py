from q1 import *
def solve(n, W, packages):
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
