package eg.gov.iti.jets.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.sql.SQLException;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.Subquery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class DatabaseManager {
    private static volatile DatabaseManager instance = null;
    private EntityManagerFactory emf;
    private EntityManager entityManager;
    public static void main(String[] args) {
        DatabaseManager.getInstance().beginTransaction();
    }

    private DatabaseManager() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
        emf = Persistence.createEntityManagerFactory("persistenceTest");
        entityManager = emf.createEntityManager();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public void flush() {
        entityManager.flush();
    }

    public void endTransaction() {
        entityManager.getTransaction().commit();
    }

    public <T> T runTransactionWithRet(Function<EntityManager, T> transaction) {
        try {

            T ret = transaction.apply(entityManager);
            return ret;
        } catch (javax.persistence.PersistenceException e) {
            e.printStackTrace();
            handleError();
        }

        // unreachable
        return null;
    }

    public void runTransaction(Consumer<EntityManager> transaction) {
        try {
            transaction.accept(entityManager);
        } catch (PersistenceException e) {
            e.printStackTrace();
            handleError();
        }
    }

    public void handleError() {
        // TODO: actually handle this f* error
        // try {
        // ThreadLocalContext.forward(ServiceNames.ERROR_REDIRECT);
        // } catch (IOException | ServletException ioException) {
        // ioException.printStackTrace();
        // }
    }
}
