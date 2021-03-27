package eg.gov.iti.jets;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface EmployeeService {
    @WebMethod
    Employee getEmployee(@WebParam(name = "id") int id);

    @WebMethod
    Employee updateEmployee(@WebParam(name = "id") int id, @WebParam(name = "name") String name);

    @WebMethod
    boolean deleteEmployee(@WebParam(name = "id") int id);

    @WebMethod
    Employee addEmployee(@WebParam(name = "name") String name);
}