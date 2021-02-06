package IONetwork.CalculatorRMI;

import IONetwork.HelloRMI.HelloInt;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class CalcImpl extends UnicastRemoteObject
        implements CalcInt {
    protected CalcImpl() throws RemoteException {
    }

    protected CalcImpl(int port) throws RemoteException {
        super(port);
    }

    protected CalcImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }
}
