# Python implementation of stack and queue
import threading
from queue import Queue as Q
from time import sleep
from tkinter import *
from tkinter import ttk

class Stack:
    
    def __init__(self):
        self.array = []
    
    def push(self, item):
        self.array.append(item)
        
    def pop(self):
        if len(self.array) > 0:
            last = self.array[-1]
            self.array.pop()
            return last
        else:
            print("Stack is empty")
    
    def peek(self):
        if len(self.array) == 0:
            print("Stack is empty")
        else:
            return self.array[-1]
    
    def count(self):
        return len(self.array)
        
    def display(self):
        print(str(self.array) + " <= top")
        
# Python implementation of queue
class Queue:
    
    def __init__(self):
        self.array = []
    
    def enqueue(self, item):
        self.array.append(item)
        
    def dequeue(self):
        if len(self.array) > 0:
            first = self.array[0]
            self.array = self.array[1:]
            return first
        else:
            print("Queue is empty")
    
    def peek(self):
        if len(self.array) == 0:
            print("Queue is empty")
        else:
            return self.array[0]

    def count(self):
        return len(self.array)

    def display(self):
        print("head => " + str(self.array))
        
class PriorityQueue(Queue):
    def __init__(self, order = "ascending"):
        self.array = []
        
        # value is True if its ascending order
        self.order = order
    
    def enqueue(self, item):
        self.array.append(item)
        
        # sort the array everytime something is enqueued, don't reverse if order is ascending
        self.array = sorted(self.array, reverse = self.order != "ascending")
        
# checks whether text contains balanced braces using stack
def check_braces(text):
    s = Stack()
    for ch in text:
        if ch == "{":
            s.push(ch)
        elif ch == "}":
            if s.count() > 0:
                s.pop()
            else:
                return False	
    return s.count() == 0

def push(li, item):
    li.insert(0,item)
        
def pop(li):
    if len(li) > 0:
        item = li[0]
        del li[0]
        return item

def peek(li):
    if len(li) > 0:
        return li[0]

def enqueue(li,item):
    li.append(item)

def dequeue(li):
    if len(li) > 0:
        item = li[0]
        del li[0]
        return item
        
def fibonacci_stack(n):
    s = Stack()
    s.push(n)
    result = 0
    while s.count() > 0:
        s.display()
        current = s.pop()
        if current == 0:
            result += 0
        elif current == 1:
            result += 1
        else:
            s.push(current - 2)
            s.push(current - 1)
    
    return result
    
def fibonacci(n):
    print(n)
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci(n-1) + fibonacci(n-2)
        
def rbsearch(a, k, lower = None, upper = None):
    if lower == None:
        lower = -1
    if upper == None:
        upper = len(a)
    mid = (lower + upper) // 2
    if mid == lower:
        return -1
    if k == a[mid]:
        return mid
    if k < a[mid]:
        return rbsearch(a, k, lower, mid)
    if k > a[mid]:
        return rbsearch(a, k, mid, upper) 

def rbsearch_stack(a, k):
    lower = -1
    upper = len(a)
    s = Stack()
    s.push(lower)
    s.push(upper)
    
    while s.count() > 0:
        upper = s.pop()
        lower = s.pop()
        mid = (lower + upper) // 2
        if mid == lower:
            return -1
        if k == a[mid]:
            return mid 
        if k < a[mid]:
            s.push(lower)
            s.push(mid)
        if k > a[mid]:
            s.push(mid)
            s.push(upper)

def is_palindrome(word):
    s = Stack()
    q = Queue()
    for ch in word:
        s.push(ch)
        q.enqueue(ch)
    while s.count() > 0:
        left = s.pop()
        right = q.dequeue()
        if left != right:
            return False
    return True
            
# visualization classes and methods 

# thread class to draw the list
class Drawer(threading.Thread):
    def __init__(self, q):
        threading.Thread.__init__(self)
        self.daemon = True
        self.q = q
   
    def run(self):
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        canvas = Canvas(root)
        canvas.grid(column=0, row=0, sticky=(N, W, E, S))
        
        # inner method called update
        def update():
            canvas.delete('all')
            
            # gets item from the queue passed by the threads
            item = self.q.get(True)
            
            # gets array from the item
            list = item
            if type(item) != type([]):
                list = item.array
            
            # represents first block (to be coloured)
            first = None
            
            # the starting Y co-ordinate for the blocks
            startY = 10
            
            # assigns the relevant labels depending on the type of list
            if len(list) > 0:
                top = ""
                bottom = ""
                if type(item) == Stack:
                    item_name = "Stack"
                    top = "Top"
                    bottom = "Bottom"
                elif type(item) == Queue:
                    item_name = "Queue"
                    top = "Head"
                    bottom = "  Tail"
                elif type(item) == PriorityQueue:
                    item_name = "Priority queue"
                    top = "Head"
                    bottom = "  Tail"
                else:
                    item_name = "List"
                    top = "Top"
                    bottom = "Bottom"
                
                # adds the top text label
                label = canvas.create_text((38, startY), text=top, anchor='nw')
                startY += 20
                    
                # reverse the list if its a stack
                if type(item) is Stack:
                    list = list[::-1]
                    
                for e in list:
                    rectangle = canvas.create_rectangle((10, startY, 90, startY + 30))
                    label = canvas.create_text((48-2.2*len(str(e)), startY + 10), text=e, anchor='nw')
                    startY += 30
                    
                    # gets reference to top block
                    if first == None:
                        first = rectangle
                    
                # colour first block to be red
                canvas.itemconfig(first, fill='red')
                
                # adds the bottom text label
                startY += 5
                label = canvas.create_text((33, startY), text=bottom, anchor='nw')
                
            else:
                item_name = ""
                if type(item) == Stack:
                    item_name = "Stack"
                elif type(item) == Queue:
                    item_name = "Queue"
                elif type(item) == PriorityQueue:
                    item_name = "Priority queue"
                else:
                    item_name = "List"
                    
                label = canvas.create_text((20, startY), text= item_name + " is empty", anchor='nw')
            
            # reupdate the drawing thread
            root.after(100, update)

        # call the update method, and mainloop to keep this method running
        root.after(100, update)
        root.mainloop()

# thread class to update the list
class ListUpdater(threading.Thread):
    def __init__(self, list, q):
        self.thread = threading.Thread.__init__(self)
        self.daemon = True
        self.list = list
        self.q = q
    
    def run(self):
        # if drawing thread is terminated
        while True:
            if self.q.empty():
                self.q.put(self.list)
                
            # to set the list to update every half second
            sleep(0.5)
    
# method to create two threads for updating list and drawing list
def view_list(list, q = Q()):
    draw_thread = Drawer(q)
    update_thread = ListUpdater(list, q)
    draw_thread.start()
    update_thread.start()
    return

