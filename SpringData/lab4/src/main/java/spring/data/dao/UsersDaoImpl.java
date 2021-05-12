//package spring.data.dao;
//
////import org.hibernate.SessionFactory;
//
//import org.hibernate.FlushMode;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
////import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.HibernateTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.support.TransactionTemplate;
//import spring.data.models.SystemUser;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository("usersDao")
//public class UsersDaoImpl implements UsersDao {
//    private static final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);
//
//
////    private org.springframework.context.ApplicationContext appContext;
////
////    private UsersDao() {
////        logger.info("appContext.getBean(\"dataSource\") = " + appContext.getBean("dataSource"));
////        jdbcTemplate = new JdbcTemplate((DataSource) appContext.getBean("dataSource"));
////        logger.info("dataSource = " + jdbcTemplate);
////    }
//
//    //    //    @Autowired
////    private JdbcTemplate jdbcTemplate;
////    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
////    private SimpleJdbcInsert jdbcInsert;
////    @Autowired
////    private SessionFactory sessionFactory;
////    @Autowired
////    private HibernateTemplate hibernateTemplate;
////    @Autowired
////    private TransactionTemplate transactionTemplate;
//
////        @Autowired
//    @PersistenceContext
//    private EntityManager entityManager;
//
////    @Autowired
////    public void setDataSource(DataSource dataSource) {
////        logger.info("dataSource = " + dataSource);
////        jdbcTemplate = new JdbcTemplate(dataSource);
////        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
////        jdbcInsert = new SimpleJdbcInsert(dataSource)
////                .withTableName("users")
////                .usingColumns("username", "password", "enabled");
////    }
//
//    @Override
////    @Transactional
//    public Long count() {
////        logger.info("jdbcTemplate = " + sessionFactory);
////        if (sessionFactory == null) return -1;
//        return (Long) entityManager.createQuery("select count(*) from SystemUser").getSingleResult();
////        var session = sessionFactory.openSession();
////        return (Long) session.createQuery("select count(*) from SystemUser").getSingleResult();
////        return (Long) sessionFactory.getCurrentSession().createQuery("select count(*) from SystemUser").getSingleResult();
////        return (Integer) sessionFactory.createEntityManager().createQuery("select count(*) from SystemUser").getSingleResult();
////        return 0;
//    }
//
//    @Override
//    public int countByEnabled() {
//        return 0;
//    }
//
//    @Override
//    // not @transactional needed now
//    @Transactional
//    public int addUser(String username, String password, int enabled) {
////        entityManager.getTransaction().begin();
//        entityManager.persist(new SystemUser(username, password, enabled));
//        entityManager.flush();
////        entityManager.getTransaction().commit();
//        return 0;
//    }
//
//    @Override
//    public List<SystemUser> getUsers() {
//        return entityManager.createQuery("from SystemUser").getResultList();
//    }
//
//    @Override
//    @Transactional
//    public void deleteUser(String username) {
////        entityManager.getTransaction().begin();
//        var obj = entityManager.find(SystemUser.class, username);
//        if (obj != null)
//            entityManager.remove(obj);
//        entityManager.flush();
////        entityManager.getTransaction().commit();
//    }
//
////    @Override
////    public int countByEnabled() {
////        return jdbcTemplate.queryForObject("select count(*) from users where enabled>0", Integer.class, 1);
////    }
////
////    @Override
////    public int addUser(String username, String password, int enabled) {
//////        return jdbcTemplate.update(
//////                "INSERT INTO users VALUES (?, ?, ?)", username, password, enabled);
//////        return namedParameterJdbcTemplate.update("INSERT INTO users VALUES (:u, :p, :e)",
//////                Map.of("u", username, "p", password, "e", enabled));
////        return jdbcInsert.execute(Map.of("username", username, "password", password, "enabled", enabled));
////    }
////
////    @Override
////    public List<SystemUser> getUsers() {
//////        return jdbcTemplate.query("select * from users", new UsersRSExtractor());
////        return jdbcTemplate.query("select * from users", new UsersMapper());
////    }
//
//}
