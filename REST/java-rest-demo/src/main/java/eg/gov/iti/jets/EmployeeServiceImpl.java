package eg.gov.iti.jets;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.dao.DatabaseManager;
import eg.gov.iti.jets.dao.EmployeeRepo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("employee")
public class EmployeeServiceImpl implements EmployeeService {

    @GET
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public List<Employee> getAll() {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emps = repo.readAll();
        db.endTransaction();
        return emps;
    }

    @GET
    @Path("{eid}")
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Employee getEmployee(@PathParam("eid") int id) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(id);
        db.endTransaction();

        if (emp.isEmpty())
            return null;
        return emp.get();
    }

    @PUT
    @Path("{eid}")
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Employee updateEmployee(@PathParam("eid") int id, String name) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(id);
        if (emp.isPresent())
            emp.get().setFirstName(name);
        db.endTransaction();

        if (emp.isEmpty())
            return null;
        return emp.get();
    }

    @PUT
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Employee updateEmployee(Employee empU) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(empU.getId());
        if (emp.isPresent())
            emp.get().setFirstName(empU.getFirstName());
        db.endTransaction();

        if (emp.isEmpty())
            return null;
        return emp.get();
    }

    @DELETE
    @Path("{eid}")
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public boolean deleteEmployee(@PathParam("eid") int id) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = repo.read(id);
        if (emp.isPresent())
            repo.delete(emp.get());
        db.endTransaction();

        if (emp.isEmpty())
            return false;
        return true;
    }

    @POST
    @Consumes({ "application/xml; qs=0.75", "application/json; qs=1" })
    @Produces({ "application/xml; qs=0.75", "application/json; qs=1" })
    public Employee addEmployee(String name) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = new Employee();
        emp.setFirstName(name);
        repo.create(emp);
        db.endTransaction();
        return emp;
    }
}
