value = float(input("Enter amount :"))

dollar = 0
cents50 = 0
cents20 = 0
cents10 = 0
cents5 = 0
cents1 = 0

dollar = int(value - value%1)

if(value-dollar) >= 0.5 :#condition with 50 cents
    cents50 = 1 # 50 cents cannot be more than 1
    cents20 = int((value-dollar-0.5)/0.2)
    cents10 =int((value-dollar-0.5-cents20*0.2)/0.1)
    cents5 =int((value-dollar-0.5-cents20*0.2-cents10*0.1)/0.05)
    cents1 =int((value-dollar-0.5-cents20*0.2-cents10*0.1-cents5*0.05)/0.01)
else: #condtion without 50 cents
    cents20 = int((value-dollar)/0.2)
    cents10 =int((value-dollar-cents20*0.2)/0.1)
    cents5 =int((value-dollar-cents20*0.2-cents10*0.1)/0.05)
    cents1 =int((value-dollar-cents20*0.2-cents10*0.1-cents5*0.05)/0.01)

if dollar != 0:
    print("Number of 1$",dollar)

if cents50 != 0:
    print("Number of 50c",cents50)

if cents20 != 0:
    print("Number of 20c",cents20)

if cents10 != 0:
    print("Number of 10c",cents10)

if cents5 != 0:
    print("Number of 5c",cents5)
    
if cents1 != 0:
    print("Number of 1c",cents1)

