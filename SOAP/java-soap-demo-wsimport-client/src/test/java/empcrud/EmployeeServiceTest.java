package empcrud;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sei.*;

public class EmployeeServiceTest {

    static EmployeeServiceImpl employeeService;

    @BeforeAll
    static void BeforeAll() {
        EmployeeServiceImplService empService = new EmployeeServiceImplService();
        employeeService = empService.getEmployeeServiceImplPort();
    }

    @Test
    void testRead() {
        var emp = employeeService.getEmployee(3);
        if (emp != null) {
            Assertions.assertEquals(emp.getId(), 3);
        } else {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void testUpdate() {
        var emp = employeeService.getEmployee(3);
        if (emp != null) {
            var newEmp = emp;
            newEmp.setFirstName("testUpdate");

            var ret = employeeService.updateEmployee(newEmp.getId(), newEmp.getFirstName());
            if (ret == null)
                Assertions.assertTrue(false);

            Assertions.assertEquals(emp.getId(), 3);
            Assertions.assertEquals(emp.getFirstName(), "testUpdate");
        } else {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void testCreateDelete() {
        var newEmp = new Employee();
        newEmp.setFirstName("testCreate");
        var empCreate = employeeService.addEmployee(newEmp.getFirstName());
        var empGet = employeeService.getEmployee(empCreate.getId());
        Assertions.assertEquals(empGet.getFirstName(), empCreate.getFirstName());
        var deleted = employeeService.deleteEmployee(empGet.getId());
        Assertions.assertEquals(deleted, true);
        var empDeleted = employeeService.getEmployee(empCreate.getId());
        Assertions.assertEquals(empDeleted, null);
    }

    @Test
    void testNull() {
        Assertions.assertEquals(null, employeeService.getEmployee(-1));
        Assertions.assertEquals(null, employeeService.updateEmployee(-1, null));
        Assertions.assertEquals(false, employeeService.deleteEmployee(-1));
    }

}