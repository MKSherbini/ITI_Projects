// package eg.gov.iti.jets;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.jws.WebMethod;
// import jakarta.jws.WebParam;
// import jakarta.jws.WebService;

// @WebService(endpointInterface = "eg.gov.iti.jets.EmployeeService")
// public class EmployeeServiceImpl implements EmployeeService {

//     private static List<Employee> emps = new ArrayList<>();

//     @Override
//     public Employee getEmployee(int id) {
//         for (Employee employee : emps) {
//             if (employee.getId() == id)
//                 return employee;
//         }
//         return null;
//     }

//     @Override
//     public Employee updateEmployee(int id, String name) {
//         for (Employee employee : emps) {
//             if (employee.getId() == id) {
//                 employee.setFirstName(name);
//                 return employee;
//             }
//         }
//         return null;
//     }

//     @Override
//     public boolean deleteEmployee(int id) {
//         for (Employee employee : emps) {
//             if (employee.getId() == id) {
//                 emps.remove(employee);
//                 return true;
//             }
//         }
//         return false;
//     }

//     @Override
//     public Employee addEmployee(int id, String name) {
//         Employee emp = new Employee();
//         emp.setFirstName(name);
//         emp.setId(id);
//         emps.add(emp);
//         return emp;
//     }
// }
