import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CalculatorClientTest {

    private static Calculator cal;

    public static void main(String[] args) {
        try {
            // Connect to the RMI registry and obtain a reference to the Calculator service
            cal = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
            Scanner scanner = new Scanner(System.in);

            // Generate a unique client ID for testing
            String clientId = "client1";
            String clientId1 = "client2";

            // Perform some operations
            System.out.println("Performing test operations for client ID: " + clientId);

            // Test 1: Push values
            System.out.println("Pushing values 10, 20, 30 onto the stack.");
            cal.pushValue(10, clientId);
            cal.pushValue(20, clientId);
            cal.pushValue(30, clientId);

            // Test 2: Check if stack is empty
            System.out.println("Is stack empty? " + cal.isEmpty(clientId));

            // Test 3: Pop a value
            System.out.println("Popped value: " + cal.pop(clientId));

            // Test 4: Push more values
            System.out.println("Pushing values 5, 15, 25 onto the stack.");
            cal.pushValue(5, clientId);
            cal.pushValue(15, clientId);
            cal.pushValue(25, clientId);

            // Test 5: Perform min operation
            System.out.println("Performing 'min' operation.");
            cal.pushOperation("min", clientId);
            System.out.println("Popped value after min operation: " + cal.pop(clientId));

            // Test 6: Push more values
            System.out.println("Pushing values 5, 15, 25 onto the stack.");
            cal.pushValue(5, clientId);
            cal.pushValue(15, clientId);
            cal.pushValue(25, clientId);

            // Test 7: Perform max operation
            System.out.println("Performing 'max' operation.");
            cal.pushOperation("max", clientId);
            System.out.println("Popped value after max operation: " + cal.pop(clientId));

            // Test 8: Push more values
            System.out.println("Pushing values 5, 12.");
            cal.pushValue(5, clientId1);
            cal.pushValue(12, clientId1);

            // Test 9: Perform delay pop
            System.out.println("Performing delayed pop operation.");
            cal.delayPop(2000, clientId1); // 2 seconds delay
            System.out.println("Popped value after delay: " + cal.pop(clientId1));

            // Test 10: Push more values
            System.out.println("Pushing values 25, 35, 45 onto the stack.");
            cal.pushValue(25, clientId1);
            cal.pushValue(35, clientId1);
            cal.pushValue(45, clientId1);

            // Test 11: Perform max operation
            System.out.println("Performing 'lcm' operation.");
            cal.pushOperation("lcm", clientId1);
            System.out.println("Popped value after lcm operation: " + cal.pop(clientId1));

            // Test 12: Push more values
            System.out.println("Pushing values 25, 35, 45 onto the stack.");
            cal.pushValue(25, clientId1);
            cal.pushValue(35, clientId1);
            cal.pushValue(45, clientId1);

            // Test 13: Perform max operation
            System.out.println("Performing 'gcd' operation.");
            cal.pushOperation("gcd", clientId1);
            System.out.println("Popped value after gcd operation: " + cal.pop(clientId1));

            // Test 14: Check if stack is empty
            System.out.println("Is stack empty? " + cal.isEmpty(clientId));

            // Test 15: Run Multiple Clients Stacks
            System.out.println("Pushing values 5, 15, 25 onto the stack for client1.");
            cal.pushValue(5, clientId);
            cal.pushValue(15, clientId);
            cal.pushValue(25, clientId);

            System.out.println("Pushing values 25, 35, 45 onto the stack for client2.");
            cal.pushValue(25, clientId1);
            cal.pushValue(35, clientId1);
            cal.pushValue(45, clientId1);


            System.out.println("Popped value for client1: " + cal.pop(clientId));
            System.out.println("Popped value for client2: " + cal.pop(clientId1));

            // Test 16: Put invalid operator
            System.out.println("Performing 'invalid' operation.");
            cal.pushOperation("invalid", clientId);

            // Cleanup
            scanner.close();

        } catch (RemoteException e) {
            System.err.println("RemoteException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}