package databaseApp.server;

import databaseApp.remote.CrudEmployeeInt;
import databaseApp.remote.Employee;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

public class CrudEmployeeImpl extends UnicastRemoteObject implements CrudEmployeeInt {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection con = databaseConnection.getConnection();

    protected CrudEmployeeImpl() throws RemoteException {
    }

    @Override
    public int addEmployee(Employee emp)throws RemoteException {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into employees values ( ? , ? , ? )");
            preparedStatement.setInt(1,emp.getId());
            preparedStatement.setString(2,emp.getName());
            preparedStatement.setString(3,emp.getAddress());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteEmployee(int id) throws RemoteException{
        try {
            PreparedStatement preparedStatement = con.prepareStatement("delete from employees where id = ?");
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateId(Employee emp) throws RemoteException{
        try {
            PreparedStatement preparedStatement = con.prepareStatement("update employees set name = ? , address = ? where id = ? ");
            preparedStatement.setString(1,emp.getName());
            preparedStatement.setString(2,emp.getAddress());
            preparedStatement.setInt(3,emp.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Employee getEmployee(int id) throws RemoteException{
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from employees where id = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                employee = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() throws RemoteException{
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Statement stmt;
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs;
            rs=stmt.executeQuery("select * from employees");

            while (rs.next()){
                employees.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
