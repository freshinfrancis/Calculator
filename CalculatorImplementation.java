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
    public void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        List<Integer> values = new ArrayList<>();
        while (!stack.isEmpty()) {
            values.add(stack.pop());
        }

        if (operator.equals("min")) {
            stack.push(Collections.min(values));
        } else if (operator.equals("max")) {
            stack.push(Collections.max(values));
        } 
        else {
            throw new RemoteException("Unknown operator: " + operator);
        }
    }

    @Override
    public int pop() throws RemoteException {
        if (stack.isEmpty()) {
            throw new RemoteException("Stack is empty.");
        }
        return stack.pop();
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

}