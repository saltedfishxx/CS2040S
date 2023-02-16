# Name: <TODO: TianFeng>
# Section: <TODO: G6>

# lab1c (Euclid's algo)

# All statements should only be in functions.
def gcd_c(x, y):
  while y != 0:
    t = y
    y = x % y
    x = t
  return x

