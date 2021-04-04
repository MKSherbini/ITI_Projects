package eg.gov.iti.jets;

public interface EmployeeService {
    Employee getEmployee(int id);

    Employee updateEmployee(int id, String name);

    boolean deleteEmployee(int id);

    Employee addEmployee(String name);
}