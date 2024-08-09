import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            CalculatorImplementation CalImpl = new CalculatorImplementation();
            Naming.rebind("CalculatorService", CalImpl);
            System.out.println("Calculator service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}