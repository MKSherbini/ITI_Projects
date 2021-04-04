package eg.gov.iti.jets;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;

@WebService(name = "WebServiceName", portName = "WebServicePortName", serviceName = "WebServiceServiceName", targetNamespace = "WebServiceNS")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED, style = Style.RPC, use = Use.LITERAL)
public interface EmployeeService {
    @WebMethod(operationName = "OperationGet")
    Employee getEmployee(@WebParam(name = "id") int id);

    @WebMethod(operationName = "OperationUpdate", exclude = true)
    Employee updateEmployee(@WebParam(name = "id") int id, @WebParam(name = "name") String name);

    @WebMethod(operationName = "OperationDelete", exclude = false)
    boolean deleteEmployee(@WebParam(name = "id") int id);

    @WebMethod(operationName = "OperationAdd", exclude = false)
    Employee addEmployee(@WebParam(name = "name") String name);
}