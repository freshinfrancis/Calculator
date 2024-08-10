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
        else if (operator.equals("gcd")) 
        {
            stack.push(gcd(values));
        }
        else if (operator.equals("lcm")) 
        {
            stack.push(lcm(values));
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
        if(stack.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private int gcd(List<Integer> values) {
        if (values.isEmpty()) return 0;
        int gcd = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            gcd = gcd(gcd, values.get(i));
        }
        return gcd;
    }

    private int gcd(int a, int b) {
        if (b == 0) 
        {
            return a;
        }
        else
        {
            return gcd(b, a % b);
        }
        
    }

    private int lcm(List<Integer> values) {
        if (values.isEmpty()) return 0;
        int lcm = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            lcm = lcm(lcm, values.get(i));
        }
        return lcm;
    }

    private int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

}