package empcrud;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eg.gov.iti.jets.Employee;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

public class EmployeeServiceTest {

    static Client client;

    @BeforeAll
    static void BeforeAll() {
        client = ClientBuilder.newClient();
    }

    @Test
    void TestRead() {
        var response = client.target("http://localhost:9090/REST-D2/api/employee").path("{id}").resolveTemplate("id", 0)
                .request(MediaType.APPLICATION_JSON).get();

        System.out.println(response.getStatus());
        if (response.getStatus() == 200) {
            var emp = response.readEntity(Employee.class);
            // System.out.println(emp);
            Assertions.assertEquals(emp.getId(), 3);
        } else {
            Assertions.assertEquals(response.getStatus(), 404);
        }
    }

    // @Test
    // void testUpdate() {
    // var emp = employeeService.getEmployee(3);
    // if (emp != null) {
    // var newEmp = emp;
    // newEmp.setFirstName("testUpdate");

    // var ret = employeeService.updateEmployee(newEmp.getId(),
    // newEmp.getFirstName());
    // if (ret == null)
    // Assertions.assertTrue(false);

    // Assertions.assertEquals(emp.getId(), 3);
    // Assertions.assertEquals(emp.getFirstName(), "testUpdate");
    // } else {
    // Assertions.assertTrue(true);
    // }
    // }

    // @Test
    // void testCreateDelete() {
    // var newEmp = new Employee();
    // newEmp.setFirstName("testCreate");
    // var empCreate = employeeService.addEmployee(newEmp.getFirstName());
    // var empGet = employeeService.getEmployee(empCreate.getId());
    // Assertions.assertEquals(empGet.getFirstName(), empCreate.getFirstName());
    // var deleted = employeeService.deleteEmployee(empGet.getId());
    // Assertions.assertEquals(deleted, true);
    // var empDeleted = employeeService.getEmployee(empCreate.getId());
    // Assertions.assertEquals(empDeleted, null);
    // }

    // @Test
    // void testNull() {
    // Assertions.assertEquals(null, employeeService.getEmployee(-1));
    // Assertions.assertEquals(null, employeeService.updateEmployee(-1, null));
    // Assertions.assertEquals(false, employeeService.deleteEmployee(-1));
    // }

}