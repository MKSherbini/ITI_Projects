package com.company.project;

import dao.*;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.DoubleType;
import org.hibernate.type.Type;
import org.jboss.logging.Logger;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.Subquery;

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
//        Seller seller = session.get(Seller.class, 21);
        List result;

//        var criteria = session.createCriteria(BuyerBuyProduct.class)
//                .add(Restrictions.eq("product", product))
//                .setProjection(Projections.sqlProjection("sum(quantity*amount) as q", col, t))
//                .list();

//        String[] col = {"q"};
//        Type[] t = {new DoubleType()};
//        var sol = session.createCriteria(BuyerBuyProduct.class)
//                .add(Restrictions.eq("product", product))
//                .setProjection(Projections.sqlProjection("sum(quantity*amount) as q", col, t))
//                .list();
//        System.out.println(sol);
//
//
//        var crit = session.createCriteria(User.class)
//                .addOrder(Order.asc("fullName"))
//                .addOrder(Order.asc("userName"));
//        System.out.println(crit.list());
//
        Criterion name = Restrictions.eq("userName", "Ahmed");
        Criteria criteria = session.createCriteria(User.class);
        criteria = criteria.add(name);
        User person = (User) criteria.uniqueResult();
        System.out.println("person = " + person);
        criteria = session.createCriteria(Seller.class)
                .add(Restrictions.eq("value", "Ahmed"));
        System.out.println(criteria.list());

        Restrictions.between("amount", 100, 200);
        Restrictions.gt("amount", 100);

        String[] emails = {"JETS@hibernate.org",
                "Medhat@hibernate.org"};
        criteria = session.createCriteria(User.class)
                .add(Restrictions.in("email", emails));
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .add(Restrictions.isNull("email"));
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .add(Restrictions.isNotNull("email"));
        System.out.println(criteria.list());

        criteria = session.createCriteria(Seller.class)
                .add(Restrictions.isEmpty("products"));
        System.out.println(criteria.list());
        criteria = session.createCriteria(Seller.class)
                .add(Restrictions.sizeGt("products", 3));
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .add(Restrictions.like("userName", "Ah%"));
        System.out.println(criteria.list());
        criteria = session.createCriteria(User.class)
                .add(Restrictions.like("userName", "J",
                        MatchMode.START));
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .add(Restrictions.eq("userName", "JETS")
                        .ignoreCase());
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .add(Restrictions.like("userName", "A%"))
                .add(Restrictions.like("fullName", "M%"));
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .add(Restrictions.or(
                        Restrictions.and(
                                Restrictions.like("userName", "A%"),
                                Restrictions.like("fullName", "M%")
                        ),
                        Restrictions.in("email", emails)
                ));
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .add(Restrictions.disjunction(
                        Restrictions.conjunction(
                                Restrictions.like("userName", "A%"),
                                Restrictions.like("fullName", "M%")
                        ),
                        Restrictions.in("email", emails)
                ));
        System.out.println(criteria.list());

        DetachedCriteria subquery =
                DetachedCriteria.forClass(Product.class, "p");
        subquery = subquery.add(Restrictions.eqProperty("p.seller.id", "s.id"))
                .add(Restrictions.isNotNull("p.buyerBidProducts"))
                .setProjection(Projections.count("p.id"));
        criteria = session.createCriteria(Seller.class, "s")
                .add(Subqueries.gt(5L, subquery));
        System.out.println(criteria.list());

        Criteria sellerCriteria = session.createCriteria(Seller.class);
        sellerCriteria = sellerCriteria.add(
                Restrictions.like("value", "M",
                        MatchMode.ANYWHERE));
        Criteria productCriteria =
                sellerCriteria.createCriteria("products");
        productCriteria = productCriteria.add(
                Restrictions.like("name", "hp",
                        MatchMode.ANYWHERE));
        result = productCriteria.list();
        System.out.println(result);


        result = session.createCriteria(Seller.class)
                .createCriteria("user")
                .add(Restrictions.like("email", "%@hibernate.org")).list();
        System.out.println(result);

        criteria = session.createCriteria(Seller.class)
                .createAlias("products", "p").add(
                        Restrictions.like("value", "%Ahmed%"))
                .add(Restrictions.like("p.name", "%hp%"));
        System.out.println(criteria.list());

        criteria = session.createCriteria(Product.class).add(
                Restrictions.lt("finishDate", new Date()))
                .setProjection(Projections.id());
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.id())
                        .add(Projections.property("userName"))
                        .add(Projections.property("fullName"))
                );
        System.out.println(criteria.list());

        criteria = session.createCriteria(User.class)
                .setProjection(Projections.rowCount());
        System.out.println(criteria.list());

        criteria = session.createCriteria(Seller.class)
                .createAlias("products", "p").setProjection(
                        Projections.projectionList()
                                .add(Property.forName("id").group())
                                .add(Property.forName("p.name").group())
                                .add(Property.forName("p.manufacturingName").group())
                                .add(Property.forName("p.buyerBidProducts").count())
                );
        System.out.println(criteria.list());

        criteria = session.createCriteria(Seller.class)
                .createAlias("products", "p").setProjection(
                        Projections.projectionList()
                                .add(Projections.groupProperty("id"))
                                .add(Projections.groupProperty("p.name"))
                                .add(Projections.groupProperty("p.manufacturingName"))
                                .add(Projections.count("p.buyerBidProducts"))
                );
        System.out.println(criteria.list());

        criteria = session.createCriteria(Seller.class)
                .createAlias("products", "p").setProjection(
                        Projections.projectionList()
                                .add(Projections.groupProperty("id"))
                                .add(Projections.groupProperty("p.name"))
                                .add(Projections.groupProperty("p.manufacturingName"))
                                .add(Projections.count("p.buyerBidProducts"))
                ).addOrder(Order.asc("p.name"));
        System.out.println(criteria.list());

        String username = "t", fullname = "t";
        criteria = session.createCriteria(User.class);
        if (username != null) {
            criteria = criteria.add(
                    Restrictions.ilike("userName",
                            username, MatchMode.ANYWHERE));
        }
        if (fullname != null) {
            criteria = criteria.add(
                    Restrictions.ilike("fullName",
                            fullname, MatchMode.ANYWHERE));
        }
        criteria = criteria.addOrder(Order.asc("userName"));
        System.out.println(criteria.list());

        // by example
        User user = new User();
        user.setUserName(username);
        user.setFullName(fullname);
        Example exampleUser = Example.create(user)
                .ignoreCase().enableLike(MatchMode.ANYWHERE)
                .excludeProperty("password");
        result = session.createCriteria(User.class)
                .add(exampleUser).list();
        System.out.println("exampleUser = " + exampleUser);
        System.out.println(result);


        exampleUser = Example.create(user)
                .ignoreCase().enableLike(MatchMode.ANYWHERE)
                .excludeProperty("password");
        result = session.createCriteria(User.class)
                .add(exampleUser).createCriteria("seller")
                .add(Restrictions.isNull("products"))
                .list();
        System.out.println(result);


        session.getTransaction().commit();
        session.close();
    }

}
