import eg.gov.iti.jets.Boss;
import eg.gov.iti.jets.Employee;
import eg.gov.iti.jets.dao.BossRepo;
import eg.gov.iti.jets.dao.DatabaseManager;
import eg.gov.iti.jets.dao.EmployeeRepo;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.getInstance().beginTransaction();
        // EmployeeRepo repo = EmployeeRepo.getInstance();
        BossRepo bossRepo = BossRepo.getInstance();
        // var emp = new Employee();
        // var boss = new Boss();
        // bossRepo.create(boss);
        // emp.setBoss(boss);
        // repo.create(emp);
        System.out.println(bossRepo.read(1));
        DatabaseManager.getInstance().endTransaction();
    }
}
