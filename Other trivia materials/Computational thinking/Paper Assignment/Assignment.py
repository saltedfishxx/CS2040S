from TreeLab import *
from LinearDSLab import *
##def inOrder(root): 
##      
##    # Set current to root of binary tree 
##    current = root  
##    stack = [] # initialize stack 
##    done = 0 
##      
##    while True: 
##          
##        # Reach the left most Node of the current Node 
##        if current is not None: 
##              
##            # Place pointer to a tree node on the stack  
##            # before traversing the node's left subtree 
##            stack.append(current) 
##          
##            current = current.left  
##  
##          
##        # BackTrack from the empty subtree and visit the Node 
##        # at the top of the stack; however, if the stack is  
##        # empty you are done 
##        elif(stack): 
##            current = stack.pop()
##            if current.():
##                print(current.value, end=" ") # Python 3 printing 
##          
##            # We have visited the node and its left  
##            # subtree. Now, it's right subtree's turn 
##            current = current.right  
##  
##        else: 
##            break
##       
##    print() 
def findleaf(root,k):
    s = Queue()
    leaf = []
    result = []
    s.enqueue(root)
    s.enqueue(None)
    level = 1
    
    while s.count() !=0 :
        node = s.dequeue()
        if(node == None):
            if s.count() != 0:
                s.enqueue(None)
            level += 1
            
        else:
            if(node.left != None):
                s.enqueue(node.left)
            
            if(node.right !=None):
                s.enqueue(node.right)
            
            if(node.isLeaf()):
                leaf.append((node.value,level))
    for r in leaf:
        if r[1] == k:
            print(r[0])

def nodelevel(root,node):
    q = Queue()
    q.enqueue(root)
    q.enqueue(None)
    level = 1
    
    while q.count() != 0:
        temp_node = q.dequeue()
        if(temp_node == None):
            if q.count() != 0:
                q.enqueue(None)
            level += 1
        else:
            if node.value == temp_node.value:
                break
            if temp_node.left != None:
                q.enqueue(temp_node.left)

            if temp_node.right != None:
                q.enqueue(temp_node.right)
    return level
    
a = Node("A")
b = Node("B")
c = Node("C")
e = Node("E")
a.setLeft(b)
a.setRight(c)
b.setLeft("D")
b.setRight("G")
c.setRight(e)
e.setLeft("F")

BT = BinaryTree()
BT.setRoot(a)

findleaf(a,3)





