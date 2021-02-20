package databaseApp.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        new Server();
    }
    public Server(){
        try{
            CrudEmployeeImpl crudEmployee = new CrudEmployeeImpl();
            Registry registry = LocateRegistry.createRegistry(8189);
            registry.bind("CrudEmployee",crudEmployee);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
