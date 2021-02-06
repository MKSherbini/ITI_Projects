package IONetwork.HelloRMI;

import javafx.application.Platform;

import java.rmi.*;
import java.rmi.registry.*;

public class Server {
    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
//            System.setProperty("java.rmi.server.hostname", "192.168.1.102");
            HelloImpl obj = new HelloImpl();
            Registry reg = LocateRegistry.createRegistry(1111);
//            Registry reg = LocateRegistry.getRegistry();
            reg.bind("HelloService", obj);
//            reg.rebind("HelloService", obj);
        } catch (RemoteException | AlreadyBoundException ex) {
            ex.printStackTrace();
        }
    }
}