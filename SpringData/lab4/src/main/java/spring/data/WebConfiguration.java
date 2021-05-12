package spring.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "spring.data.dao")
@EnableJpaRepositories(basePackages = "spring.data.dao")
@ComponentScan("spring.data")
@ImportResource("classpath:app-context.xml")
public class WebConfiguration implements WebMvcConfigurer {
    @Bean(value = "dataSource", autowireCandidate = true)
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty(
//                "hbm2ddl.auto", "none");
        hibernateProperties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
        return hibernateProperties;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() { // todo PersistenceContext from here too
//        var factory = new LocalSessionFactoryBean();
//        factory.setDataSource(getDataSource());
////        factory.setMappingResources("");
//        factory.setPackagesToScan("spring.data");
//        factory.setHibernateProperties(hibernateProperties());
//        return factory;
//    }
//
//    @Bean
//    public PlatformTransactionManager hibernateTransactionManager() {
//        HibernateTransactionManager transactionManager
//                = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public HibernateTemplate hibernateTemplate() {
//        var factory = new HibernateTemplate();
//        factory.setSessionFactory(sessionFactory().getObject());
//        return factory;
//    }
//
//    @Bean
//    public TransactionTemplate transactionTemplate() {
//        var factory = new TransactionTemplate();
//        factory.setTransactionManager(hibernateTransactionManager());
//        return factory;
//    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        var factory = new Factory();
//        return factory.getEntityManagerFactory();
//    }
//
//    @Bean
//    public EntityManager entityManager() {
//        return entityManagerFactory().createEntityManager();
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("spring.data");
        factory.setJpaProperties(hibernateProperties());
        factory.setDataSource(getDataSource());
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factory;
    }
//
//    @Bean
//    public EntityManager entityManager() {
//        return entityManagerFactoryBean().createNativeEntityManager(null); // todo null?
//    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}