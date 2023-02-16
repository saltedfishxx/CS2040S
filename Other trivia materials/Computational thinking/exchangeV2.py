def countdollar(amount):
    
    print("Number of 1$",int(amount))#get integer part of amount
    if(amount%1 == 0.09999999999999964):
        count10cents(0.1)
    else:
        if(amount - int(amount)) >= 0.5): #condtion for 50c above
            
            count50cents(amount - int(amount)) #passing in the remainder
            
        else:#condtion for 50c below

            count20cents(amount - int(amount))

def count50cents(amount):

    if(amount >= 0.5):
        print("Number of 50c",1) #maximum of 1 50cents
        count20cents(amount-0.5)
    else:
        count20cents(amount)

def count20cents(amount):

    if(amount >= 0.4 ):
        print("Number of 20c",2) #if amount > or = 0.4 it must be two 20cents
        count10cents(amount-0.4)
    elif(amount >= 0.2):
        print("Number of 20c",1) #if amount > or = 0.2 it must be one 20cents
        count10cents(amount-0.2)
    else: #less than 0.2 and 0.4, no 20 cents pass to next
        count10cents(amount)

def count10cents(amount):

    if(amount >= 0.1):
        print("Number of 10c",1) #can only be 1 as two 10cents is 10cents
        count5cents(amount-0.1)
    else:
        count5cents(amount)

def count5cents(amount):

    if(amount >= 0.05):
        print("Number of 5c",1) #can only be 1 as two 5cents is 10cents
        count1cents(amount-0.05)
    else:
        count1cents(amount)

def count1cents(amount):

    if(amount != 0.0):

        print("Number of 1c",int(amount/0.01))

value = float(input("Enter amount :"))
countdollar(value)
