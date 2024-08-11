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



