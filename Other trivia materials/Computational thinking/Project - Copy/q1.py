# <Your Team ID>
# <Team members' names>

# Q1

# Replace the content of this function with your own algorithm
# inputs: 
#   W: weight limit of the vehicle used for deliveries.
#   packages: 2D list [[packageID, reward, weight], [packageID, reward, weight], ...]
# returns:
#   1D list of package IDs to represent a package selection. e.g. ["P001", "P003, "P010]

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
  

  



# you may insert other functions here, but all statements must be within functions
# before submitting to red, check that there are no print statements in your code. Nothing should be printed when your code runs.
