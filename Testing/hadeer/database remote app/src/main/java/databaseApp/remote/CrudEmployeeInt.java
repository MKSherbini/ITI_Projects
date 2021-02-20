package databaseApp.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CrudEmployeeInt extends Remote {
    public int addEmployee (Employee emp)throws RemoteException;
    public int deleteEmployee(int id)throws RemoteException;
    public int updateId(Employee emp)throws RemoteException;
    public Employee getEmployee(int id)throws RemoteException;
    public ArrayList<Employee> getAllEmployees()throws RemoteException;
}
