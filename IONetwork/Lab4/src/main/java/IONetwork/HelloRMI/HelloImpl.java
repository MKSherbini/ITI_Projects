package IONetwork.HelloRMI;

import java.rmi.*;
import java.rmi.server.*;

public class HelloImpl extends UnicastRemoteObject
        implements HelloInt {
    public HelloImpl() throws RemoteException {
//super();
    }

    public String sayHello(String name) {
        return "Hello" + name;
    }
}