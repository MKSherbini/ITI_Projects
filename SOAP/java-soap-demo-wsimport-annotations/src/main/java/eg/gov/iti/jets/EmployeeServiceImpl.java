package eg.gov.iti.jets;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.dao.DatabaseManager;
import eg.gov.iti.jets.dao.EmployeeRepo;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;

@WebService(endpointInterface = "eg.gov.iti.jets.EmployeeService")
// @SOAPBinding(parameterStyle = ParameterStyle.WRAPPED, style = Style.RPC, use = Use.ENCODED)
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee getEmployee(int id) {
        EmployeeRepo repo = EmployeeRepo.getInstance();
        DatabaseManager.getInstance().beginTransaction();
        var emp = repo.read(id);
        DatabaseManager.getInstance().endTransaction();

        if (emp.isEmpty())
            return null;
        return emp.get();
    }

    @Override
    public Employee updateEmployee(int id, String name) {
        EmployeeRepo repo = EmployeeRepo.getInstance();
        DatabaseManager.getInstance().beginTransaction();
        var emp = repo.read(id);
        if (emp.isPresent())
            emp.get().setFirstName(name);
        DatabaseManager.getInstance().endTransaction();

        if (emp.isEmpty())
            return null;
        return emp.get();
    }

    @Override
    public boolean deleteEmployee(int id) {
        EmployeeRepo repo = EmployeeRepo.getInstance();
        DatabaseManager.getInstance().beginTransaction();
        var emp = repo.read(id);
        if (emp.isPresent())
            repo.delete(emp.get());
        DatabaseManager.getInstance().endTransaction();

        if (emp.isEmpty())
            return false;
        return true;
    }

    @Override
    public Employee addEmployee(String name) {
        DatabaseManager.getInstance().beginTransaction();
        EmployeeRepo repo = EmployeeRepo.getInstance();
        var emp = new Employee();
        emp.setFirstName(name);
        repo.create(emp);
        DatabaseManager.getInstance().endTransaction();
        return emp;
    }
}
