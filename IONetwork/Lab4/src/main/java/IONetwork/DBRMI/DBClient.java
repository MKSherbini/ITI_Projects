package IONetwork.DBRMI;

import IONetwork.CalculatorRMI.CalcInt;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DBClient {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry(1111);
            DBInt db = (DBInt) reg.lookup("DBInt");

            var emps = db.getEmpList();
            emps.forEach(System.out::println);

            db.AddEmp(11, "10");
            db.DeleteEmp(2);
            db.UpdateEmp(4, "ali");

            System.out.println("emp = " + db.getEmp(10));

            emps = db.getEmpList();
            emps.forEach(System.out::println);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

}

