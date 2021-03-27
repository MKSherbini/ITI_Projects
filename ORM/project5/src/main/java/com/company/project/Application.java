package com.company.project;

import dao.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        session.getTransaction().begin();

//        User user = new User("test");
//        BuyerBuyProduct buyerBuyProduct = new BuyerBuyProduct();
//        BuyerBuyProductId buyerBuyProductId = new BuyerBuyProductId();
//        BuyerBidProduct buyerBidProduct = new BuyerBidProduct();
//        BuyerBidProductId buyerBidProductId = new BuyerBidProductId();
//        Category category = new Category();
//        Product product = new Product();
//        Buyer buyer = new Buyer();
//        Seller seller = new Seller();

//        var session = fact.openSession();
//        session.getTransaction().begin();
//        user.getBuyers().add(buyer);
//        user.setSeller(seller);
//        buyer.setUser(user);
//        buyer.setValue("test");
//        buyer.getBuyerBuyProducts().add(buyerBuyProduct);
//        buyer.getBuyerBidProducts().add(buyerBidProduct);
//        seller.getProducts().add(product);
//        seller.setUser(user);
//        product.getCategories().add(category);
//        product.getBuyerBuyProducts().add(buyerBuyProduct);
//        product.getBuyerBidProducts().add(buyerBidProduct);
//        product.setSeller(seller);
//        category.getProducts().add(product);
//        buyerBuyProduct.setBuyer(buyer);
//        buyerBuyProduct.setProduct(product);
//        buyerBidProduct.setBuyer(buyer);
//        buyerBidProduct.setProduct(product);
//        buyerBidProduct.setId(buyerBidProductId);
//        buyerBuyProduct.setId(buyerBuyProductId);
//
//        session.save(user);
//        session.save(seller);
//        session.save(buyer);
//        session.save(product);
//        session.save(category);
//        buyerBuyProductId.setProductId(product.getId());
//        buyerBuyProductId.setBuyerId(buyer.getId());
//        session.save(buyerBuyProduct);
//        buyerBidProductId.setProductId(product.getId());
//        buyerBidProductId.setBuyerId(buyer.getId());
//        session.save(buyerBidProduct);

        // get seller of a product
        var product = session.load(Product.class, 21);
        Seller seller = session.get(Seller.class, 21);
        List result;
        result = session.createQuery("from Seller s where :givenProduct in (select p from s.products p)")
                .setEntity("givenProduct", product).list();
        System.out.println("result = " + result);

        // fetch product where all seller's value starts has pattern t
        result = session.createQuery("from Product p where p.seller = all (from Seller s where s.value like '%t%')")
                .list();
        System.out.println("result all = " + result);

        // fetch product where a seller's value starts has pattern t
        result = session.createQuery("from Product p where p.seller = any (from Seller s where s.value like '%t%')")
                .list();
        System.out.println("result any = " + result);

        // fetch product where a seller's value starts has pattern t
        result = session.createQuery("from Product p where p.seller = some (from Seller s where s.value like '%t%')")
                .list();
        System.out.println("result some = " + result);

        // fetch product where a seller's value starts has pattern t
        result = session.createQuery("from Product p where p.seller in (from Seller s where s.value like '%t%')")
                .list();
        System.out.println("result in = " + result);

        // The outer query returns all Sellers who offered
        // products more than 2 products that it’s name
        // contains “t”
        result = session.createQuery("from Seller s where ( select count(p) from s.products p where p.name Like '%t%') > 2")
                .list();
        System.out.println("result 2 queries = " + result);

        // create object from data returned
        result = session.createQuery("select new AbstractBuyedProduct(p.name,\n" +
                "size(p.buyerBuyProducts), p.manufacturingDate)\n" +
                "from Product p\n" +
                "where p.buyerBuyProducts is not empty")
                .list();
        System.out.println("result AbstractBuyedProduct = " + result);


        // select buyer name and length of products after grouping the buyers by the names
        result = session.createQuery("select b.value, count(b.value)\n" +
                "from Buyer b\n" +
                "group by b.value\n"
                + "having count(b.value) > 4"
        )
                .list();
        System.out.println("result grouping = " + result);


        // select buyer name and length of products
        result = session.createQuery("select b.value, size(b.buyerBuyProducts) from Buyer b group by\n" +
                "b.value having b.value like '%t%'")
                .list();
        System.out.println("result grouping 2 = " + result);


        // select buyer name and length of products
        result = session.createQuery("select b.value, size(b.buyerBuyProducts) from Buyer b group\n" +
                "by b.value")
                .list();
        System.out.println("result grouping 3 = " + result);

        // select product and seller that has names like %t%
        result = session.createQuery("from Product p join p.seller s where s.value like\n" +
                "'%t%' and p.name like '%t%'")
                .list();
        System.out.println("result join = " + result);

        // select product and seller that has a name like %t%
        result = session.createQuery("from Product p, Seller s where p.seller = s and s.value\n" +
                "like '%t%'")
                .list();
        System.out.println("result join 2 = " + result);

        // product join seller where seller name is like t and product name is like t
        result = session.createQuery("from Product p left join p.seller s with s.value like\n" +
                "'%t%' where p.name like '%t%'")
                .list();
        System.out.println("result join 3 = " + result);

        // product join seller where seller name is like t and product name is like t
        result = session.createQuery("from Product p join p.seller s where s.value like\n" +
                "'%t%' and p.name like '%t%'")
                .list();
        System.out.println("result join 4 = " + result);

        // seller's user email like t
        result = session.createQuery("from Seller s where s.user.email like '%t%'")
                .list();
        System.out.println("result email like = " + result);

        // find sum of bought products
        result = session.createQuery("select sum(buy.amount) from BuyerBuyProduct buy")
                .list();
        System.out.println("result sum = " + result);


        // find number of sellers for products
        result = session.createQuery("select count(p.seller) from Product p")
                .list();
        System.out.println("result like = " + result);


        // select both product and buyer
        result = session.createQuery("from Product p, BuyerBidProduct bid")
                .list();
        System.out.println("result like = " + result);


        // select users and order by username asc and fullname desc
        result = session.createQuery("from User u order by u.userName  asc , u.fullName desc")
                .list();
        System.out.println("result  like = " + result);


        // select products with categories more than 1
        result = session.createQuery("from Product p where size(p.categories) = 1")
                .list();
        
        System.out.println("result  like = " + result);


        // select products with name start with t and manufacturing name with same t
        result = session.createQuery("from Product p where upper(p.name) like 't%' or p.manufacturingName like 't%'")
                .list();
        System.out.println("result  like = " + result);


        // select products with categories
        result = session.createQuery("from Product p where p.categories is not empty")
                .list();
        System.out.println("result  like = " + result);


        // select products with name like t
        result = session.createQuery("from Product p where p.name like 't%'")
                .list();
        System.out.println("result email like = " + result);


        // select products with name like %t
        result = session.createQuery("from Product p where p.name like '#%t%' escape '#'")
                .list();
        System.out.println("result email like = " + result);


        // select products with name is p1
        result = session.createQuery("from Product p where p.name like 'p1'")
                .list();
        System.out.println("result email like = " + result);


        // select user with the seller object
        result = session.createQuery("from User u where u.seller = :sellerKey")
                .setEntity("sellerKey", seller)
                .list();
        System.out.println("result email like = " + result);


        // select products with name like product2 and older manufacturer date
        result = session.createQuery("from Product p where p.name like :name and p.manufacturingDate < :date")
                .setString("name", "product2")
                .setDate("date", new Date(1990, Calendar.JANUARY, 1))
                .list();
        System.out.println("result email like = " + result);


        // select seller with val val
        result = session.createQuery("from Seller s where s.value= :val")
                .setString("val", "val")
                .list();
        System.out.println("result email like = " + result);


        session.getTransaction().commit();
        session.close();
    }

}
