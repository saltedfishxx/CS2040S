def calculate_sums(num_list):
    if len(num_list) == 0:
        return []
    result = []
    add_num = 0
    for i in range(len(num_list)):

        add_num += num_list[i]
        result.append(add_num)
    return result


tuple1 = (1,2,3,4)
list1 = []
list2 = list(tuple1)
for i in range(len(tuple1)):

    list1.append(tuple1[i])

print(type(tuple1))
print(tuple1)
print()

print(type(list1))
print(list1)
print()

print(type(list2))
print(list2)
