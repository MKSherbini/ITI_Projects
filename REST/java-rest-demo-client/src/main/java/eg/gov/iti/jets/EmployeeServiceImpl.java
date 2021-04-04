package eg.gov.iti.jets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eg.gov.iti.jets.dao.DatabaseManager;
import eg.gov.iti.jets.dao.EmployeeRepo;
import eg.gov.iti.jets.exceptions.ResourceNotFoundException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("employee")
public class EmployeeServiceImpl implements EmployeeService {

    @GET
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Response getAll(@Context UriInfo uriInfo) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emps = repo.readAll();
        db.endTransaction();
        emps.forEach(e -> e.setLinks(Arrays.asList(new CustomLink("self", uriInfo.getAbsolutePath()))));
        GenericEntity<List<Employee>> ge = new GenericEntity<List<Employee>>(emps) { };
        return Response.ok().entity(ge).build();
    }

    @GET
    @Path("{eid}")
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Response getEmployee(@PathParam("eid") int id, @Context UriInfo uriInfo) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(id);
        db.endTransaction();

        if (emp.isEmpty())
            throw new ResourceNotFoundException("Order with ID:" + id + " Not Found");
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        emp.get().setLinks(Arrays.asList(new CustomLink("self", uriInfo.getAbsolutePath())));
        return Response.ok().entity(emp.get()).build();
    }

    @PUT
    @Path("{eid}")
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Response updateEmployee(@PathParam("eid") int id, String name, @Context UriInfo uriInfo) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(id);
        if (emp.isPresent())
            emp.get().setFirstName(name);
        db.endTransaction();

        if (emp.isEmpty())
            throw new ResourceNotFoundException("Order with ID:" + id + " Not Found");
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        emp.get().setLinks(Arrays.asList(new CustomLink("self", uriInfo.getAbsolutePath())));
        return Response.ok().entity(emp.get()).build();
    }

    @PUT
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Response updateEmployee(Employee empU, @Context UriInfo uriInfo) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(empU.getId());
        if (emp.isPresent())
            emp.get().setFirstName(empU.getFirstName());
        db.endTransaction();

        if (emp.isEmpty())
            throw new ResourceNotFoundException("Order with ID:" + empU.getId() + " Not Found");
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        emp.get().setLinks(Arrays.asList(new CustomLink("self", uriInfo.getAbsolutePath())));
        return Response.ok().entity(emp.get()).build();
    }

    @DELETE
    @Path("{eid}")
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Response deleteEmployee(@PathParam("eid") int id, @Context UriInfo uriInfo) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(id);
        if (emp.isPresent())
            repo.delete(emp.get());
        db.endTransaction();

        if (emp.isEmpty())
            throw new ResourceNotFoundException("Order with ID:" + id + " Not Found");
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        emp.get().setLinks(Arrays.asList(new CustomLink("self", uriInfo.getAbsolutePath())));
        return Response.ok().entity(emp.get()).build();
    }

    @POST
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Response addEmployee(String name, @Context UriInfo uriInfo) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = new Employee();
        emp.setFirstName(name);
        repo.create(emp);
        db.endTransaction();
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        emp.setLinks(Arrays.asList(new CustomLink("self", uriInfo.getAbsolutePath())));
        return Response.ok().entity(emp).build();
    }
}
