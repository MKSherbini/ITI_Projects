package IONetwork.CalculatorRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalcServer {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1111);
            reg.rebind("CalcInt", new CalcImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
