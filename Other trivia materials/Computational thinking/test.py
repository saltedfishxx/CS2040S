def celsius(f):
    if (f<0):
        print("Error number")
        return -1
    x = f - 32
    x = x *5/9
    return x

 #function with return


def square(x): #takes in 1 variable
    
    #calculate x square
    "given an input x, return the square of x (doc string)"
    #doc string in a function will appear when calling help(fuctionName)
    
    return x**2

def square2num(x,y): #takes in 2 variable 
    return x*x,y*y

# Note: Not to do
# return x*x
# return y*y

''''''''''''''''''''''''''''''''''''''''''
#function with no return
def f1():
    
    print("practice your labs")
    
    #Print statement is not a return statement.
    #You cant assign a variable to print


def boolean():
    
    result = 10>5

    #to check if result is true
    result == True

    # Note: using one "=" is assigning a value to a variable


def tax_rate(income):
    print(income)# will be printed
    if income < 10000:
        return 0.0
    elif income < 20000:
        return 5.0
    else:
        return 7.0
    print(income) #will not reach here as the function terminate after a return

#altering strings
def altstrings():
    s = "hello"
    print(s+s)
    print(s*5)
    print(len(s))

    name = "Fiona Lee"
    surname = "Lee" #Note use same "" when declaring a string. Not '"

    print(surname in name) #returns a true false value
    name.count("e")#counting a character in a string
    #or
    str.count(name,"a")

    name = 'Fiona Lee "@smu"'
    print(name) # Fiona Lee "@smu"

    name = "Fiona Lee '@smu'"
    print(name) # Fiona Lee '@smu'

    # can use \ to escape characters
    name = " ***Fiona Lee \"@smu\"***"
    print(name)


#testing data types
def datatype(income):
    if (type(income) !=int):
        print(income)
        return 0
    if income < 10000:
        return 0.0
    elif income < 20000:
        return 5.0
    else:
        return 7.0
    
