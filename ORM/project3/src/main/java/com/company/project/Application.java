package com.company.project;

import dao.*;
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

        User user = new User("test");
        BuyerBuyProduct buyerBuyProduct = new BuyerBuyProduct();
        BuyerBuyProductId buyerBuyProductId = new BuyerBuyProductId();
        BuyerBidProduct buyerBidProduct = new BuyerBidProduct();
        BuyerBidProductId buyerBidProductId = new BuyerBidProductId();
        Category category = new Category();
        Product product = new Product();
        Buyer buyer = new Buyer();
        Seller seller = new Seller();

        var session = fact.openSession();
        session.getTransaction().begin();
        user.getBuyers().add(buyer);
        user.setSeller(seller);
        buyer.setUser(user);
        buyer.setValue("test");
        buyer.getBuyerBuyProducts().add(buyerBuyProduct);
        buyer.getBuyerBidProducts().add(buyerBidProduct);
        seller.getProducts().add(product);
        seller.setUser(user);
        product.getCategories().add(category);
        product.getBuyerBuyProducts().add(buyerBuyProduct);
        product.getBuyerBidProducts().add(buyerBidProduct);
        product.setSeller(seller);
        category.getProducts().add(product);
        buyerBuyProduct.setBuyer(buyer);
        buyerBuyProduct.setProduct(product);
        buyerBidProduct.setBuyer(buyer);
        buyerBidProduct.setProduct(product);
        buyerBidProduct.setId(buyerBidProductId);
        buyerBuyProduct.setId(buyerBuyProductId);

        session.save(user);
        session.save(seller);
        session.save(buyer);
        session.save(product);
        session.save(category);
        buyerBuyProductId.setProductId(product.getId());
        buyerBuyProductId.setBuyerId(buyer.getId());
        session.save(buyerBuyProduct);
        buyerBidProductId.setProductId(product.getId());
        buyerBidProductId.setBuyerId(buyer.getId());
        session.save(buyerBidProduct);

        session.getTransaction().commit();
        session.close();
    }

}
