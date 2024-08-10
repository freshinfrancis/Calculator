import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    
    private Stack<Integer> stack;

    protected CalculatorImplementation() throws RemoteException {
        stack = new Stack<>();  
    }  

    @Override
    public synchronized void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    @Override
    public synchronized void pushOperation(String operator) throws RemoteException {
        List<Integer> values = new ArrayList<>();
        while (!stack.isEmpty()) {
            values.add(stack.pop());
        }
        
        if (operator.equals("min")) 
        {
            int min = Collections.min(values);
            stack.push(min);
        } 
        else if (operator.equals("max")) 
        {
            int max = Collections.max(values);
            stack.push(max);
        } 
        else {
            throw new RemoteException("Unknown operator: " + operator);
        }
    }

    @Override
    public synchronized int pop() throws RemoteException {
        if (stack.isEmpty()) {
            throw new RemoteException("Stack is empty.");
        }
        return stack.pop();
    }

    @Override
    public synchronized boolean isEmpty() throws RemoteException {
        if(stack.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}