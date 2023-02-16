# Name: <TODO: TianFeng>
# Section: <TODO: G6>

# lab0a

# All statements should only be in functions. Do not include statements outside functions in this file.
# fill up the admit method to return either True or False depending on the sex and age
def admit(sex,age):
  #Male
  if sex == "M":
    if age > 22:
      result = True
    else :
      result = False
  #Female
  else :
    if age > 17:
      result = True
    else :
      result = False
  return result
