# Name: <TODO: TianFeng>
# Section: <TODO: G6>

# lab1b (Dijkstra's algo)

# All statements should only be in functions.
def gcd_b(x, y):
  while x != y:
    if x > y:
      x = x-y
    else:
      y = y-x
  return x
print("Running test cases for Q1(b) - Dijkstra's algo:")
print("Test case 1 passed :", gcd_b(5352, 6690) == 1338)        # test case 1. 1338
print("Test case 2 passed :", gcd_b(7800111, 393945) == 78789)  # test case 2. 78789
print("Test case 3 passed :", gcd_b(75116, 6752) == 844)        # test case 3. 844
print("Test case 4 passed :", gcd_b(7999992, 1999998) == 1999998)# test case 4. 1999998
print("Test case 5 passed :", gcd_b(2, 6) == 2)                 # test case 5. 2
print("Test case 6 passed :", gcd_b(1000, 1) == 1)              # test case 6. 1
print()
