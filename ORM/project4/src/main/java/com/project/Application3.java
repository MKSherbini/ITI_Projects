package com.project;

import com.project.fetching.Seller;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;

public class Application3 {
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
        var session2 = fact.openSession();
        Seller seller = session.load(Seller.class, 1);
        System.out.println("seller = " + seller);
        System.out.println("seller.getProducts().size() = " + seller.getProducts().size());
        session.close();
        Seller seller2 = session2.find(Seller.class, 1);
        System.out.println("seller2 = " + seller2);
        System.out.println("seller.getProducts().size() = " + seller2.getProducts().size());
        session2.close();
    }


}
