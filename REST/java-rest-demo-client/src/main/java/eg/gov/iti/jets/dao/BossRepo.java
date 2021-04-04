package eg.gov.iti.jets.dao;

import java.util.Optional;

import eg.gov.iti.jets.Boss;
import eg.gov.iti.jets.Employee;

public class BossRepo extends GenericRepo<Boss, Integer> {
    private static volatile BossRepo instance = null;

    private BossRepo() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
    }

    public static BossRepo getInstance() {
        if (instance == null) {
            synchronized (BossRepo.class) {
                if (instance == null) {
                    instance = new BossRepo();
                }
            }
        }
        return instance;
    }

    public Optional<Boss> findByName(String name) {
        return DatabaseManager.getInstance().runTransactionWithRet(session -> (Optional<Boss>) session
                .createNamedQuery("Boss.findByName").setParameter("name", name).getResultList().stream().findAny());
    }
}
