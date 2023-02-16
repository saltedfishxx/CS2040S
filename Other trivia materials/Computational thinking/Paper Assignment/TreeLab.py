import threading
from queue import Queue
from tkinter import *
from tkinter import ttk
from time import sleep


class Drawer(threading.Thread):
    def __init__(self, q):
        threading.Thread.__init__(self)
        self.daemon = True
        self.q = q
        self.h = dict()
    
    def run(self):
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        root.geometry('500x500')
        canvas = Canvas(root)
        hbar=Scrollbar(canvas,orient=HORIZONTAL)
        hbar.pack(side=BOTTOM,fill=X)
        hbar.config(command=canvas.xview)
        vbar=Scrollbar(canvas,orient=VERTICAL)
        vbar.pack(side=RIGHT,fill=Y)
        vbar.config(command=canvas.yview)
        canvas.config(yscrollcommand=vbar.set)
        canvas.config(xscrollcommand=hbar.set)
        canvas.grid(column=0, row=0, sticky=(N, W, E, S))
        def update():
            canvas.delete('all')
            self.tree = self.q.get(True)
            w = 30+(2**self.tree.heightOfTree())*30
            h = 50*self.tree.heightOfTree()+50
            canvas.config(width= w, height= h, scrollregion=(0,0,w,h))
            self.preorder(((2**self.tree.heightOfTree())*30)/2, 30, ((2**self.tree.heightOfTree())*30)/2, canvas, hash, self.tree.root)
            root.after(100, update)
        root.after(100, update)
        root.mainloop()
        
    
        
    def preorder(self, x, y, width, canvas, hash, node, visited = []):
        if node != None:
            arr = []
            self.h[node.value] = arr
            arr.append(canvas.create_oval(x - 15, y - 15, x + 15, y + 15))
            arr.append(canvas.create_text(x, y, anchor='center', text=node.value))
            arr.append([x, y])
            if node.left != None:
                canvas.create_line(x-15, y, x-(width/2), y+50-15)
            self.preorder(x-(width/2), y+50, width/2, canvas, hash, node.left, visited)
            if node.right != None:
                canvas.create_line(x+15, y, x+(width/2), y+50-15)
            self.preorder(x+(width/2), y+50, width/2, canvas, hash, node.right, visited)
            
class TraversalDrawer(Drawer):
    def __init__(self, traversalQueue, tree, step, title):
        super().__init__(traversalQueue)
        self.tree = tree
        self.updateArr = []
        self.step = step
        self.title = title
        self.daemon = True

    def run(self):
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        root.title(self.title)
        root.geometry('500x500')
        canvas = Canvas(root)
        hbar=Scrollbar(canvas,orient=HORIZONTAL)
        hbar.pack(side=BOTTOM,fill=X)
        hbar.config(command=canvas.xview)
        vbar=Scrollbar(canvas,orient=VERTICAL)
        vbar.pack(side=RIGHT,fill=Y)
        vbar.config(command=canvas.yview)
        canvas.config(yscrollcommand=vbar.set)
        canvas.config(xscrollcommand=hbar.set)
        canvas.grid(column=0, row=0, sticky=(N, W, E, S))
        self.preorder(((2**self.tree.heightOfTree())*30)/2, 30, ((2**self.tree.heightOfTree())*30)/2, canvas, hash, self.tree.root)
        def update():
            # canvas.delete('all')
            if self.q.empty():
                return
            self.updateArr.append(self.q.get(True))
            # print(self.h)
            # print(self.updateArr)
            for i in range(1, len(self.updateArr) + 1):
                value = self.updateArr[i - 1]
                # print('value: ' + str(value))
                canvas.itemconfig(self.h[value][0], outline='red')
                # canvas.itemconfig(self.h[value][1], fg='red')
                canvas.create_text(self.h[value][2][0], self.h[value][2][1] + 25, anchor='center', text=str(i))
            root.after(self.step, update)
        root.after(self.step, update)
        root.mainloop()


class BinaryTree:
    def __init__(self):
        self.root = None
        self.queue = Queue()
    
    def setRoot(self, node):
        if not isinstance(node, Node):
            node = Node(node)
        self.root = node

    def root(self):
        return self.root 
        
    def countHeight(self, node):
        if node == None:
            return 0
        if node.isLeaf():
            return 1
        return 1 + max(self.countHeight(node.left), self.countHeight(node.right))      

    def heightOfTree(self):
        if self.root == None:
            return 0
        return self.countHeight(self.root)
    

    def countNode(self, node):
        if node == None:
            return 0
        if node.isLeaf():
            return 1
        return 1 + self.countNode(node.left) + self.countNode(node.right)

    def noOfNodes(self):
        if self.root == None:
            return 0
        return self.countNode(self.root)

    def isEmpty(self):
        return self.root == None
        
    def __str__(self):
        if self.root == None:
            return None
        return str(self.root)

class Node:
    def __init__(self, value):
        self.left = None
        self.right = None
        self.value = value
    
    def setLeft(self, leftChild):
        """
        Set the left child of this node
        """
        if not isinstance(leftChild, Node):
            leftChild = Node(leftChild)
        self.left = leftChild

    def setRight(self, rightChild):
        """
        Set the right child of this node
        """
        if not isinstance(rightChild, Node):
            rightChild = Node(rightChild)
        self.right = rightChild

    def delLeft(self):
        """
        Remove the left child of this node
        """
        self.left = None

    def delRight(self):
        """
        Remove the right child of this node
        """
        self.right = None

    def isLeaf(self):
        """
        Return True if this node is a leaf node. False otherwise.
        """
        return self.left == None and self.right == None
        
    def __repr__(self):    
        s = "Value: " + str(self.value)
        if self.left != None:
            s += ", left child: " + str(self.left.value)
        else:
            s += ", left child: None"
        if self.right != None:
            s += ", right child: "+ str(self.right.value)
        else:
            s += ", right child: None"
        return s

class BinarySearchTree(BinaryTree):
    def __init__(self):
        BinaryTree.__init__(self)
    
    def isStable(self):
        """
        Returns True if this tree is stable. False otherwise.
        """
        self.stableChecker = []
        self.__inOrderCheck(self.root)
        for i in range(1, len(self.stableChecker)):
            if self.stableChecker[i] < self.stableChecker[i-1]:
                return False
        return True

    def search(self, key):
        """
        Starts an animation to show how a BinarySearchTree search is implemented
        """
        if not self.isStable():
            raise Error("Warning! Binary Search tree is not sorted correctly.") 
        q = Queue()
        traversalThread = TraversalDrawer(q, self, 1000, 'Search')
        visited = []
        traversalThread.start()
        _bstsearch(self.root, key, visited, q)
        print(searchbst(self.root, key))

    def insert(self, key):
        """
        Inserts a a node into this tree
        """
        if not self.isStable():
            print("Warning! Binary Search tree is not sorted correctly.") 
        if not isinstance(key, Node):
            key = Node(key)
        if self.root == None:
            self.root = key
            return
        node = insertbst(self.root, key)
        return node
        
    def remove(self, key):
        """
        Removes a node from this tree
        """
        if not self.isStable():
            raise Error("Warning! Binary Search tree is not sorted correctly.") 
        if self.root.value == key:
            if self.root.left == None and self.root.right == None:
                self.root = None
            elif self.root.left == None:
                self.root = self.root.right 
            elif self.root.right == None:
                self.root = self.root.left 
            else:
                left = self.root.left 
                self.root = self.root.right 
                node = self.root
                while node.left != None:
                    node = node.left 
                node.left = left 
        else:
            removebst(self.root, key)
                
    def __inOrderCheck(self, root):
        if root != None:
            self.__inOrderCheck(root.left)
            self.stableChecker.append(root.value)
            self.__inOrderCheck(root.right)
            
def removebst(root, key):
    if root == None:
        return
    if root.left.value == key:
        if root.left.left == None and root.left.right == None:
            root.left = None 
        elif root.left.left == None:
            root.left = root.left.right 
        elif root.left.right == None:
            root.left = root.left.left 
        else:
            left = root.left.left
            root.left = root.left.right 
            node = root.left
            while node.left != None:
                node = node.left 
            node.left = left 
    elif root.right.value == key:
        if root.right.left == None and root.right.right == None:
            root.right = None 
        elif root.right.left == None:
            root.right = root.right.right 
        elif root.right.right == None:
            root.right = root.right.left 
        else:
            left = root.right.left
            root.right = root.right.right 
            node = root.right
            while node.left != None:
                node = node.left 
            node.left = left 
    elif key > root.value:
        removebst(root.right, key)
    else:
        removebst(root.left, key)

            
def insertbst(root, key):
    """
    Insert a node with value key into a tree with root root
    """
    if root.value == key.value:
        return root
    elif root.value > key.value:
        if root.left != None:
            return insertbst(root.left, key)
        else:
            root.setLeft(key)
            return key
    else:
        if root.right != None:
            return insertbst(root.right, key)
        else:
            root.setRight(key)
            return key
           
def searchbst(root, key):
    if root.value == key:
        return root
    elif root.left != None and root.value > key:
        return searchbst(root.left, key)
    elif root.right != None and root.value < key:
        return searchbst(root.right, key)
    else:
        return None
           
def _preorder_traverse(node, visited, q = None):
    if node != None:
        visit(node, visited, q)
        _preorder_traverse(node.left, visited, q)
        _preorder_traverse(node.right, visited, q)

def preorder_traverse(node):
    """
    Display an animation of preorder traversal of a tree with root node
    """
    q = Queue()
    tree = BinaryTree()
    tree.setRoot(node)
    traversalThread = TraversalDrawer(q, tree, 1000, 'preorder')
    visited = []
    traversalThread.start()
    _preorder_traverse(node, visited, q)
        
def _postorder_traverse(node, visited, q = None):
    if node != None:
        _postorder_traverse(node.left, visited, q)
        _postorder_traverse(node.right, visited, q)
        visit(node, visited, q)
        
def postorder_traverse(node):
    """
    Display an animation of postorder traversal of a tree with root node
    """
    q = Queue()
    tree = BinaryTree()
    tree.setRoot(node)
    traversalThread = TraversalDrawer(q, tree, 1000, 'postorder')
    visited = []
    traversalThread.start()
    _postorder_traverse(node, visited, q)
        
def _inorder_traverse(node, visited, q = None):
    if node != None:
        _inorder_traverse(node.left, visited, q)
        visit(node, visited, q)
        _inorder_traverse(node.right, visited, q)
        
def inorder_traverse(node):
    """
    Display an animation of inorder traversal of a tree with root node
    """
    q = Queue()
    tree = BinaryTree()
    tree.setRoot(node)
    traversalThread = TraversalDrawer(q, tree, 1000, 'inorder')
    visited = []
    traversalThread.start()
    _inorder_traverse(node, visited, q)
        
def _bstsearch(node, val, visited, q):
    if node != None:
        visit(node, visited, q)
        if val == node.value:
            pass
        elif val < node.value:
            _bstsearch(node.left, val, visited, q)
        else:
            _bstsearch(node.right, val, visited, q)
        
        
def visit(node, visited, q):
    visited.append(node.value)
    if q != None:
        q.put(node.value)

    
class TreeUpdater(threading.Thread):
    def __init__(self, tree, q):
        threading.Thread.__init__(self)
        self.daemon = True
        self.tree = tree
        self.q = q
    
    def run(self):
        while True:
            if self.q.empty():
                sleep(1)
                self.q.put(self.tree)
                #print('updated!')
            
def view_tree(tree, q = Queue()):
    update_thread = TreeUpdater(tree, q)
    draw_thread = Drawer(q)
    draw_thread.start()
    update_thread.start()
    return True
    