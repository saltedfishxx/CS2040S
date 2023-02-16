def knapSack(W, wt, val, n): 
  
    # Base Case 
    if n == 0 or W == 0: 
        return 0
  
    # If weight of the nth item is 
    # more than Knapsack of capacity W, 
    # then this item cannot be included 
    # in the optimal solution 
    if (wt[n-1] > W): 
        return knapSack(W, wt, val, n-1) 
  
    # return the maximum of two cases: 
    # (1) nth item included 
    # (2) not included 
    else:
        temp1 = val[n-1] + knapSack( 
                W-wt[n-1], wt, val, n-1)
        temp2 = knapSack(W, wt, val, n-1)
        if temp1> temp2:
        return max(temp1,temp2) 
  
# end of function knapSack 
  
  
#Driver Code 
val = [24, 13, 23, 15, 16]
wt = [12, 7, 11, 8, 9]
W = 26
n = len(val) 
print(knapSack(W, wt, val, n))
