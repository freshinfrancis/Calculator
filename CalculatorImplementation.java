import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    
    private final Map<String, Stack<Integer>> stacks;

    protected CalculatorImplementation() throws RemoteException {
        super();
        stacks = new HashMap<>();  
    }  

    private Stack<Integer> getStackForClient(String clientId) {
        Stack<Integer> stack = stacks.get(clientId);
        if (stack == null) {
            stack = new Stack<>();
            stacks.put(clientId, stack);
        }
        return stack;
    }

    @Override
    public synchronized void pushValue(int val, String clientId) throws RemoteException {
        getStackForClient(clientId).push(val);
    }

    @Override
    public synchronized void pushOperation(String operator, String clientId) throws RemoteException {
        
        Stack<Integer> stack = getStackForClient(clientId);

        if (stack.isEmpty()) 
        {
            System.out.println("Error: Stack is empty for operation " + operator);
            return;
        }

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
    public synchronized int pop(String clientId) throws RemoteException {
        Stack<Integer> stack = getStackForClient(clientId);
        if (stack.isEmpty()) {
            throw new RemoteException("Stack is empty.");
        }
        return stack.pop();
    }

    @Override
    public synchronized boolean isEmpty(String clientId) throws RemoteException {
        if(getStackForClient(clientId).isEmpty())
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