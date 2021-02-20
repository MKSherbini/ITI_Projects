package databaseApp.client;

import databaseApp.remote.CrudEmployeeInt;
import databaseApp.remote.Employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    CrudEmployeeInt crudEmployee;

    public static void main(String[] args) throws RemoteException {
        Client client = new Client();
        int userChoice = Integer.MAX_VALUE;
        while (userChoice != 0) {
            System.out.println("1- get all employees");
            System.out.println("2- get employee");
            System.out.println("3- update employee");
            System.out.println("4- delete employee");
            System.out.println("5- add new employee");
            System.out.println("0- exit");
            System.out.print("Please enter your choice:");
            Scanner in = new Scanner(System.in);
            userChoice = in.nextInt();
            if (userChoice < 0 || userChoice > 5) {
                System.out.println("Please enter valid choice");
            }
            switch (userChoice) {
                case 1:
                    client.getAll();
                    break;
                case 2:
                    client.getEmployee();
                    break;
                case 3:
                    client.updateEmployee();
                    break;
//                case 4:
//                    client.deleteEmployee();
//                    break;
//                case 5:
//                    client.insertEmployee();
//                    break;

            }
        }


    }

    public Client() {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 8189);
            crudEmployee = (CrudEmployeeInt) reg.lookup("CrudEmployee");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void getAll() throws RemoteException {
        ArrayList<Employee> employees = null;
//        try {
        employees = crudEmployee.getAllEmployees();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i));
        }
    }

    public void getEmployee() throws RemoteException {
        int id = chooseEmp();
//        try {
        System.out.println(crudEmployee.getEmployee(id));
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }

    public void updateEmployee() throws RemoteException {
        getAll();
        int id = chooseEmp();
        try {
            System.out.println(crudEmployee.getEmployee(id));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.print("Enter emp name:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.print("Enter emp address");
        String address = in.nextLine();
        try {
            crudEmployee.updateId(new Employee(id, name, address));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) throws RemoteException {
        getAll();
//        int id = chooseEmp();
//        try {
        crudEmployee.deleteEmployee(id);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }

    public void insertEmployee(String name, String address) throws RemoteException {
        ArrayList<Employee> employees = null;
//        try {
        employees = crudEmployee.getAllEmployees();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
        int id = employees.get(employees.size() - 1).getId() + 1;
//        System.out.print("Enter emp name:");
//        Scanner in = new Scanner(System.in);
//        String name = in.nextLine();
//        System.out.print("Enter emp address");
//        String address = in.nextLine();
        try {
            crudEmployee.addEmployee(new Employee(id, name, address));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int chooseEmp() {
        System.out.println("Please enter Id of Employee");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        return id;
    }

}
