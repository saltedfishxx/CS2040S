# Name: <TODO: TianFeng>
# Section: <TODO: G6>

# lab0b

# All statements should only be in functions. Do not include statements outside functions in this file.
# fill up the weight_category method to return either "underweight", "overweight" or "normal" 
# depending on the height (in cm) and weight (in kg)
def weight_category(weight, height):
  #convert height to M
  h = height/100.0
  bMI = weight / (h*h)
  #underweight
  if(bMI < 18.5):
    result = "underweight"
  #normal
  if (bMI > 18.5 and bMI <=25):
    result = "normal"
  #overweight
  if(bMI > 25):
    result = "overweight"
  return result
