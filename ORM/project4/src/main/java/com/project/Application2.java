package com.project;

import com.project.fetching.Product;
import com.project.fetching.Seller;
import com.project.inheritance4.Student;
import com.project.inheritance4.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;

public class Application2 {
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
        session.getTransaction().begin();


//        Seller seller = new Seller();
//        seller.setId(1);
//        session.save(seller);
//
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product();
//            product.setId(i + 1);
////            seller.getProducts().add(product);
//            product.setSeller(seller);
//            session.save(product);
//        }

        Seller seller = session.load(Seller.class, 1);
        System.out.println("seller.getProducts().size() = " + seller.getProducts().size());
//        for (Product product : seller.getProducts()) {
//            System.out.println("product = " + product);
//        }
        session.getTransaction().commit();
        session.close();
    }


}
