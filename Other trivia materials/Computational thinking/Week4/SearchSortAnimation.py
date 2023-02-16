from tkinter import *
from tkinter import ttk
from time import sleep

def lSearch(array, target, animate = False):
    if animate:
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        root.geometry('500x300')
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
        canvas.master.title("Linear Search Animation")
    for i in range(0, len(array)):
        if animate:
            canvas.delete("all")
            root.after(0,draw_Search_list(array,canvas,root, i) )
            root.update()
            sleep(0.5)
        if array[i] == target:
            if animate:
                canvas.delete("all")
                root.after(0,draw_Search_list(array,canvas,root, i, True) )
                root.update()
                sleep(0.5)
                root.mainloop()
            return i
    if animate:
        canvas.delete("all")
        root.after(0,draw_Search_list(array,canvas,root, len(array)+1) )
        root.update()
        sleep(0.5)
        root.mainloop()
    return -1
    
    
def bSearch(array, target, animate = False):
    if animate:
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        root.geometry('500x300')
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
        canvas.master.title("Binary Search Animation")    
    lower = -1
    upper = len(array)
    while not (lower + 1 == upper):
        mid = (lower+upper)//2
        if animate:
            canvas.delete("all")
            root.after(0,draw_Search_list(array,canvas,root, mid ) )
            root.update()
            sleep(0.5)
        if target == array[mid]:
            if animate:
                canvas.delete("all")
                root.after(0,draw_Search_list(array,canvas,root, mid, True) )
                root.update()
                sleep(0.5)
                root.mainloop()
            return mid
        elif target < array[mid]:
            upper = mid
        else:
            lower = mid
    if animate:
        canvas.delete("all")
        root.after(0,draw_Search_list(array,canvas,root, len(array)+1) )
        root.update()
        sleep(0.5)
        root.mainloop()
    return -1

    
def iSort(array, animate=False):
    if animate:
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        root.geometry('500x300')
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
        canvas.master.title("Insertion Sort Animation")    
    i = 1
    while i < len(array):
        j = i
        while j > 0 and array[j] < array[j-1]:
            if animate:
                canvas.delete("all")
        
            array[j],array[j-1] = array[j-1], array[j]
            j -= 1
            
            if animate:
                root.after(0,draw_list(array,canvas,root, j))
                root.update()
                sleep(1)
        i += 1

    if animate:
        root.after(0,draw_list(array,canvas,root, -1))
        root.update()
        root.mainloop()
    
    return array
   
	
def mSort(array, animate = False):
    if animate:
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        root.geometry('500x300')
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
        canvas.master.title("Merge Sort Animation")

    groupsize = 1
    while groupsize < len(array):
        if animate:
            mergeGroups(array, groupsize, animate, root, canvas)
        else:
            mergeGroups(array,groupsize)
        groupsize *= 2
    if animate:
        root.mainloop()
    return array

def mergeGroups(array, groupsize, animate = False, root = None, canvas = None):
    i = 0
    while i < len(array):
        j = i + 2 * groupsize
        if animate:
            canvas.delete("all")
            root.after(0,draw_mlist(array,canvas,root, groupsize,i,j,0))
            root.update()
            sleep(0.5)
        array[i:j] = merge(array, i, groupsize)
        if animate:
            canvas.delete("all")
            root.after(0,draw_mlist(array,canvas,root,groupsize,i,j,1))
            root.update()
            sleep(0.5)
        i = i + (2 * groupsize)

def merge(array, i ,groupsize):
    resultArray = []
    firstGroup = array[i:i+groupsize]
    secondGroup = array[i+groupsize:i+groupsize*2]
    while (len(firstGroup) != 0 or len(secondGroup) != 0):
        if(len(firstGroup) == 0):
            while (len(secondGroup) != 0):
                resultArray.append(secondGroup.pop(0))
        elif (len(secondGroup) == 0):
            while (len(firstGroup) != 0):
                resultArray.append(firstGroup.pop(0))
        else:
            if(firstGroup[0] > secondGroup[0]):
                resultArray.append(secondGroup.pop(0))
            else:
                resultArray.append(firstGroup.pop(0))
    return resultArray

    
    
def qSort(array, low = None, high = None, animate = True, root = None, canvas = None, base = 0):
    if animate and root == None and canvas == None:
        root = Tk()
        root.columnconfigure(0, weight=1)
        root.rowconfigure(0, weight=1)
        root.geometry('500x300')
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
        canvas.master.title("Quick Sort Animation")
    if low == None:
        low = 0
    if high == None:
        high = len(array)-1
    if low < high:
        p = pivot(array, low, high, animate, root, canvas)
        base += 1
        qSort(array, low, p-1, animate, root, canvas, base)
        qSort(array, p+1, high, animate, root, canvas, base)
        base -= 1
    if base == 0 and animate:
        root.mainloop()
    return array

def pivot(array, low, high, animate, root, canvas):
    min = low - 1
    currPivot = high
    for i in range(low, high):
        if animate:
            canvas.delete("all")
            root.after(0,draw_qlist(array,canvas,root, currPivot, i, min, -1))
            root.update()
            sleep(0.8)
        if array[i] <= array[high]:
            min += 1
            
            if animate:
                canvas.delete("all")
                if min != i:
                    root.after(0,draw_qlist(array,canvas,root,currPivot,i, min, i))
                else:
                    root.after(0,draw_qlist(array,canvas,root,currPivot,i, min, -1))
                root.update()
                sleep(0.8)
            array[min],array[i] = array[i], array[min]
            if animate:
                canvas.delete("all")
                root.after(0,draw_qlist(array,canvas,root,currPivot,i, min, -1))
                root.update()
                sleep(0.8)
    if animate:
        canvas.delete("all")
        root.after(0,draw_qlist(array,canvas,root,currPivot,i, min + 1, high))
        root.update()
        sleep(0.8)
    array[min+1],array[high] = array[high], array[min+1]

    return(min+1)
    

def draw_Search_list(array, canvas, root, index, found = False):
    '''Drawing for Search'''
    canvas.grid(column=0, row=0, sticky=(N, W, E, S))
    arr = []
    startX = 20
    bar_length = 15
    max_height = 100
    max_value = max(array)
    counter = 0
    if len(array) > 0:
            
        # creates rectangle for every element in the stack, in reverse order
        for e in array:
            if(counter > 0):
                canvas.delete(label)
            bar_height = max_height * (e / max_value)
            label = ""
            if(index != len(array)+1):
                label = canvas.create_text((startX + bar_length, max_height+20), text="Current index: " + str(index) + "\nCurrent element: " + str(array[index]), anchor='nw')
            if (counter == index):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="blue")
                canvas.delete(label)
            elif (index == len(array) + 1):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="red")
            else:
            # white rectangles are padded from top of window by 20 pixels
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="white")
            
            currElement = canvas.create_text((startX + 10, max_height +30), text = str(e))
            arr.append(rectangle)
            startX += bar_length
            counter += 1
        
        
        if (index == len(array) + 1):
            label = canvas.create_text((startX + bar_length, max_height+20), text="Not Found", anchor='nw')
        elif (found):
            canvas.delete(label)
            label = canvas.create_text((startX + bar_length, max_height+20), text="Found " + str(array[index]) + " at index: " + str(index), anchor='nw')
            
            
    else:
        label = canvas.create_text((20, 20), text="Empty list", anchor='nw')
    
    
def draw_list(array, canvas, root, swapIndex):
    canvas.grid(column=0, row=0, sticky=(N, W, E, S))
    arr = []
    startX = 20
    bar_length = 15
    max_height = 100
    max_value = max(array)
    counter = 0
    if len(array) > 0:
            
        # creates rectangle for every element in the stack, in reverse order
        for e in array:
            if(counter > 0):
                canvas.delete(label)
            bar_height = max_height * (e / max_value)
            label = ""
            if (swapIndex != -1):
                label = canvas.create_text((startX + bar_length + 5, max_height+20), text="Current index: " + str(swapIndex) + "\nCurrent element: " + str(array[swapIndex]) + "\nSwapped with: " + str(array[swapIndex+1]) + "\nNext element to check: " + str(array[swapIndex-1]), anchor='nw')
            else:
                label = canvas.create_text((startX + bar_length + 5, max_height), text="Array is sorted", anchor='nw')
            if (counter == swapIndex):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="purple")
            elif(swapIndex == -1):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="blue")
            else:
            # white rectangles are padded from top of window by 20 pixels
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="white")
                
            currElement = canvas.create_text((startX + 10, max_height +30), text = str(e))
            arr.append(rectangle)
            startX += bar_length
            counter+=1
            
    else:
        label = canvas.create_text((20, 20), text="Empty list", anchor='nw')
        
def draw_mlist(array, canvas, root, groupsize, i, j, step):
    canvas.grid(column=0, row=0, sticky=(N, W, E, S))
    arr = []
    startX = 20
    bar_length = 15
    max_height = 100
    max_value = max(array)
    counter = 0
    if len(array) > 0:            
        for e in array:
            if(counter > 0):
                canvas.delete(label)
            bar_height = max_height * (e / max_value)
            label = ""
            #labeling
            #if (i != None):
                #label = canvas.create_text((startX + bar_length + 5, max_height), text="Current group size: " + str(groupsize), anchor='nw', font=("Helvetica", 11))

            #coloring
            firstGroupMin = i
            firstGroupMax = i + groupsize - 1
            secondGroupMin = i + groupsize
            secondGroupMax = i + groupsize*2 - 1
            if (step == 1 and (counter >= firstGroupMin and counter <= firstGroupMax)):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="purple")

            elif (counter >= firstGroupMin and counter <= firstGroupMax):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="yellow")
            elif (counter >= secondGroupMin and counter <= secondGroupMax):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="blue")
            else:
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="white")
            
            currElement = canvas.create_text((startX + 10, max_height +30), text = str(e))
            arr.append(rectangle)
            startX += bar_length
            counter+=1
            
    else:
        label = canvas.create_text((20, 20), text="Empty list", anchor='nw')

def draw_qlist(array, canvas, root, pivot, currPoint, swap1, swap2):
    canvas.grid(column=0, row=0, sticky=(N, W, E, S))
    arr = []
    startX = 20
    bar_length = 15
    max_height = 100
    max_value = max(array)
    counter = 0
    if len(array) > 0:            
        for e in array:
            if(counter > 0):
                canvas.delete(label)
            bar_height = max_height * (e / max_value)
            label = ""
            #labeling
            if (swap2 != -1 and swap1 < swap2):
                label = canvas.create_text((startX + bar_length + 5, max_height), text="Current pivot index: " + str(pivot) + "\nCurrent pivot element: " + str(array[pivot]) + "\n Swapping " + str(array[swap1]) + " with " + str(array[swap2]), anchor='nw', font=("Helvetica", 11))
            else:
                label = canvas.create_text((startX + bar_length + 5, max_height), text="Current pivot index: " + str(pivot) + "\nCurrent pivot element: " + str(array[pivot]), anchor='nw', font=("Helvetica", 11))
            #coloring
            if (counter < currPoint and counter != swap1 and swap2 == -1 ):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="white")
            elif (counter == pivot):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="purple")
            elif(swap2 == -1 and counter == swap1):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="green")
            elif ((counter == swap1 or counter == swap2)):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="yellow")
            elif (counter == currPoint):
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="blue")
            else:
                rectangle = canvas.create_rectangle((startX, max_height-bar_height+20, startX + bar_length, max_height+20), fill="white")
            currElement = canvas.create_text((startX + 10, max_height +30), text = str(e))
            arr.append(rectangle)
            startX += bar_length
            counter+=1
            
    else:
        label = canvas.create_text((20, 20), text="Empty list", anchor='nw')
