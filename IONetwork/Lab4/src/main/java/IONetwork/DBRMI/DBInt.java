package IONetwork.DBRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DBInt extends Remote {
    void AddEmp(int id, String name) throws RemoteException;

    void UpdateEmp(int id, String newName) throws RemoteException;

    void DeleteEmp(int id) throws RemoteException;

    Emp getEmp(int id) throws RemoteException;

    List<Emp> getEmpList() throws RemoteException;
}