package IONetwork.DBRMI;

import IONetwork.CalculatorRMI.CalcImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DBServer {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1111);
            reg.rebind("DBInt", new DBImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
