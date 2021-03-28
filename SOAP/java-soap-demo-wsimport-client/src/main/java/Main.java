import sei.EmployeeServiceImplService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Main.main()");
        EmployeeServiceImplService service = new EmployeeServiceImplService();
        service.getEmployeeServiceImplPort().getEmployee(3);

        // DatabaseManager.getInstance().beginTransaction();
        // // EmployeeRepo repo = EmployeeRepo.getInstance();
        // BossRepo bossRepo = BossRepo.getInstance();
        // // var emp = new Employee();
        // // var boss = new Boss();
        // // bossRepo.create(boss);
        // // emp.setBoss(boss);
        // // repo.create(emp);
        // System.out.println(bossRepo.read(1));
        // DatabaseManager.getInstance().endTransaction();
    }
}
