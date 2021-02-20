package databaseApp.client;

import databaseApp.remote.CrudEmployeeInt;
import databaseApp.remote.Employee;
import databaseApp.server.Server;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientTest extends TestCase {
    Client client;
    CrudEmployeeInt crudEmployee;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        new Server();
        client = new Client();
        Registry reg = LocateRegistry.getRegistry("localhost", 8189);
        crudEmployee = (CrudEmployeeInt) reg.lookup("CrudEmployee");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(timeout = 2000)
    public void testGetAll() {
        try {
            client.getAll();
        } catch (RemoteException e) {
            fail();
        }
//        Throwable exception = Assert.assertThrows(RemoteException.class, () -> {
//        });

//        exception.expect(RemoteException.class);
    }

    @Test(timeout = 2000)
    public void testUpdate() {
        ArrayList<Employee> employees = null;
        try {
            employees = crudEmployee.getAllEmployees();
        } catch (RemoteException e) {
            fail();
        }
        int id = 1;
        if (employees.size() != 0)
            id = employees.get(0).getId();
        else return;
        String name = "testName";
        String address = "testAddress";
        var emp = employees.get(0);
        emp.setName("ali");
        try {
            crudEmployee.updateId(emp);
        } catch (RemoteException e) {
            fail();
        }

        try {
            var emp2 = crudEmployee.getEmployee(id);
            assertEquals(emp, emp2);
        } catch (RemoteException e) {
            fail();
        }
    }

    @Test(timeout = 2000)
    public void testInsert() {
        ArrayList<Employee> employees = null;
        try {
            employees = crudEmployee.getAllEmployees();
        } catch (RemoteException e) {
            fail();
        }
        int id = 1;
        if (employees.size() != 0)
            id = employees.get(employees.size() - 1).getId() + 1;
        String name = "testName";
        String address = "testAddress";
        var emp = new Employee(id, name, address);
        try {
            crudEmployee.addEmployee(emp);
        } catch (RemoteException e) {
            fail();
        }
    }

    @Test(timeout = 2000)
    public void testAll() {
        ArrayList<Employee> employees = null;
        try {
            employees = crudEmployee.getAllEmployees();
        } catch (RemoteException e) {
            fail();
        }
        int id = 1;
        if (employees.size() != 0)
            id = employees.get(employees.size() - 1).getId() + 1;
        String name = "testName";
        String address = "testAddress";
        var emp = new Employee(id, name, address);
        try {
            crudEmployee.addEmployee(emp);
        } catch (RemoteException e) {
            fail();
        }
        try {
            var emp2 = crudEmployee.getEmployee(id);
            assertEquals(emp, emp2);
        } catch (RemoteException e) {
            fail();
        }

        try {
            crudEmployee.deleteEmployee(id);
        } catch (RemoteException e) {
            fail();
        }
    }

    @Test(timeout = 2000)
    public void testDelete() {
        ArrayList<Employee> employees = null;
        try {
            employees = crudEmployee.getAllEmployees();
        } catch (RemoteException e) {
            fail();
        }
        if (employees.size() == 0)
            return;

        var emp = employees.get(0);

        try {
            crudEmployee.deleteEmployee(emp.getId());
        } catch (RemoteException e) {
            fail();
        }

        try {
            var emp2 = crudEmployee.getEmployee(emp.getId());
            assertNull(emp2);
        } catch (RemoteException e) {
            fail();
        }
    }

    @Test(timeout = 2000)
    public void testDeleteAll() {
        ArrayList<Employee> employees = null;
        try {
            employees = crudEmployee.getAllEmployees();
        } catch (RemoteException e) {
            fail();
        }
        if (employees.size() == 0)
            return;

        employees.forEach(employee -> {
            try {
                crudEmployee.deleteEmployee(employee.getId());
            } catch (RemoteException e) {
                fail();
            }
        });

        try {
            crudEmployee.deleteEmployee(1);
        } catch (RemoteException e) {
            fail();
        }

        try {
            crudEmployee.updateId(employees.get(0));
        } catch (RemoteException e) {
            fail();
        }
        try {
            crudEmployee.getEmployee(1);
        } catch (RemoteException e) {
            fail();
        }
    }

}