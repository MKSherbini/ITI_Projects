package spring.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("persistence");
    }
}
