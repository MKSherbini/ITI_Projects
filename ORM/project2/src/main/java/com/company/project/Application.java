package com.company.project;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    static Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws SQLException {
        ServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("/hibernate.cfg.xml")
                        .build();
        Metadata metadata =
                new MetadataSources(standardRegistry).buildMetadata();
        SessionFactory fact =
                metadata.getSessionFactoryBuilder().build();

        var session = fact.openSession();

//        Account account = new Account("test7");
//        session.save(account);

        if (session.isOpen())
            session.close();
        else
            System.out.println("closed");
    }

}
