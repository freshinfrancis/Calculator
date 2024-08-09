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

