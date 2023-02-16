# Name: <TODO: replace with your name>
# Section: <TODO: replace with your section>

# lab4

# All statements should only be in functions. Do not include statements outside functions in this file.


def select_tweeters(followers):
    ans = []
    temp = []
    
    for i in range(0,len(followers)):
        temp.append([i,len(followers[i])])
    temp.sort(key=lambda x: -x[1])
    
    ans = [temp[0][0],temp[1][0],temp[2][0],temp[3][0],temp[4][0]]
    return ans
