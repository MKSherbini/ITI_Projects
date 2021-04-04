package eg.gov.iti.jets;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

public interface EmployeeService {
    Response getEmployee(int id, @Context UriInfo uriInfo);

    Response updateEmployee(int id, String name, @Context UriInfo uriInfo);

    Response deleteEmployee(int id, @Context UriInfo uriInfo);

    Response addEmployee(String name, @Context UriInfo uriInfo);
}