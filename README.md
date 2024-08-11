# Calculator
A Simple RMI Calculator

# First tried to test Java RMI by creating a calculator which does the basic operations
Created interface with calling methods accepting two values for addition, subtraction, division and multiplication.
In the implementation file, wrote the actions that each method performs.
Created Java Server File where it is instructed to run the server on 1099 and called the implementation class.
In the Java Client File, gave the values for the operations manually and called various exception through try catch.
Java RMI works successfully and returns the outcome of operations.

# JAVA RMI Calculator 

# Calculator.Java
1] Imported java.rmi.Remote to indicate that an interface is intended for remote use. Extending Remote allows methods can be invoked from other JVMs (Java Virtual Machines) over a network.

2] Imported java.rmi.RemoteException and declared with a throw clause helps to find communication-related issue that has occurred during the method call.

3] Next, called all the remote methods that has been instructed to be created in the Assignment which are as follows:

void pushValue(int val)
void pushOperation(String operator)
int pop()
boolean isEmpty()
int delayPop(int millis)

# CalculatorImplementation.java
This code implements a remote calculator with client-specific stacks using Java RMI. It uses a HashMap to efficiently store stacks for each client (clientId).
All methods are synchronized to ensure thread-safety during concurrent access. Error handling is implemented for various scenarios like empty stacks, invalid operations, and interrupted exceptions.

getStackForClient: This method retrieves the existing stack for a client or creates a new one if it doesn't exist.

pushValue: Pushes a new value onto the client's stack.

pop: Pops the top element from the client's stack and throws an exception if the stack is empty.

pushOperation: Handles various operations (min, max, gcd, lcm):
Check for an empty stack and printing an error message (consider throwing an exception instead of just printing).
Create a temporary ArrayList to store popped values for efficient calculation.
Using the Collections.min and Collections.max methods for finding minimum and maximum values.
Call the gcd and lcm helper methods for calculating greatest common divisor and least common multiple.
Throw a RemoteException for unknown operators.

isEmpty: Checks if the client's stack is empty.

delayPop: This method introduces a delay before popping from the stack:
It uses TimeUnit.MILLISECONDS.sleep to pause for the specified millis.
It catches potential InterruptedException and throws a RemoteException with the cause for better handling in the client.
After the delay, it calls pop to retrieve the element from the stack.

# CalculatorServer.java
Creates a Registry: Sets up a registry on port 1099 to act as a directory for remote objects.

Instantiates a Calculator Implementation: Creates an object of the CalculatorImplementation class, which presumably contains the logic for calculator operations.

Registers the Object: Binds the CalculatorImplementation object to the registry with the name "CalculatorService". This makes it accessible to remote clients.


# CalculatorClient.java
This program acts as a client for the RMI calculator service.

UUID.randomUUID().toString(): This generates a unique client ID using a Universally Unique Identifier. This helps differentiate client interactions with the server.

Naming.lookup: Looks up the "CalculatorService" object from the RMI registry running on localhost (the same machine).

# Output

Compile the java files and then start rmiregistry

<img width="647" alt="image" src="https://github.com/user-attachments/assets/46648109-3584-4171-bc89-1775281f286f">


Start the Server

<img width="626" alt="image" src="https://github.com/user-attachments/assets/4e718d73-4d28-4939-ab77-71c05bc35a37">


Start the Client. Push Values on the stack, pop value from the stack and check if stack is empty

<img width="640" alt="image" src="https://github.com/user-attachments/assets/4bbc4018-73cd-4f77-b0fa-90b29a12e7ad">


delayPop Operation

<img width="643" alt="image" src="https://github.com/user-attachments/assets/6effd497-55d8-4ac9-8b43-31b2db9d0627">

Try min pushOperation and check if stack is empty after the min value is popped.

<img width="626" alt="image" src="https://github.com/user-attachments/assets/7ef327a0-9da1-4851-84d9-712dd6119981">

Try max pushOperation and check if stack is empty after the min value is popped.

<img width="598" alt="image" src="https://github.com/user-attachments/assets/5bb3ad28-caf6-4d30-bd4b-6900077841bc">







