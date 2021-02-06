package IONetwork.CalculatorRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalcInt extends Remote {
    default int Add(int a, int b) throws RemoteException {
        return a + b;
    }

    default int Subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    default int Multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    default int Divide(int a, int b) throws RemoteException {
        return a / b;
    }
}
