package JavaGUI.ChatingRMI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServer {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1112);
            reg.rebind("ChatServerInt", new ChatServerImpl());
            System.out.println("Server started");
        } catch (RemoteException e) {
            System.out.println("Server failed");
            e.printStackTrace();
        }
    }
}
