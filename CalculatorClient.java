import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;

public class CalculatorClient {
    public static void main(String[] args) {
        try {

            String clientId = UUID.randomUUID().toString(); 
            Calculator cal = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter command (pushValue, pushOperation, pop, isEmpty, delayPop, exit):");
                String input = scanner.nextLine();

                switch (input) {
                    case "pushValue":
                        System.out.println("Enter value:");
                        int value = Integer.parseInt(scanner.nextLine());
                        cal.pushValue(value, clientId);
                        break;
                    case "pushOperation":
                        System.out.println("Enter operation (min, max, lcm, gcd):");
                        String operator = scanner.nextLine();
                        cal.pushOperation(operator, clientId);
                        break;
                    case "pop":
                        System.out.println("Popped value: " + cal.pop(clientId));
                        break;
                    case "isEmpty":
                        System.out.println("Is stack empty? " + cal.isEmpty(clientId));
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Error! Unknown command.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}