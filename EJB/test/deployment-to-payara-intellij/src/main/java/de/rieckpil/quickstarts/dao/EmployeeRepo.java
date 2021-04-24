package de.rieckpil.quickstarts.dao;

import java.util.Optional;

import eg.gov.iti.jets.Employee;

public class EmployeeRepo extends GenericRepo<Employee, Integer> {
    private static volatile EmployeeRepo instance = null;

    private EmployeeRepo() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
    }

    public static EmployeeRepo getInstance() {
        if (instance == null) {
            synchronized (EmployeeRepo.class) {
                if (instance == null) {
                    instance = new EmployeeRepo();
                }
            }
        }
        return instance;
    }

    public Optional<Employee> findByName(String name) {
        return DatabaseManager.getInstance().runTransactionWithRet(
                session -> (Optional<Employee>) session.createNamedQuery("Employee.findByName")
                        .setParameter("name", name).getResultList().stream().findAny());
    }
}
