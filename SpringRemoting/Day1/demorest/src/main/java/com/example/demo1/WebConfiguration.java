package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demo1.database")
@ImportResource("classpath:app-context.xml")
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

//    @Bean(value = "dataSource", autowireCandidate = true)
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
//        dataSource.setUsername("admin");
//        dataSource.setPassword("admin");
//        return dataSource;
//    }

//    @Bean
//    public Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
////        hibernateProperties.setProperty(
////                "hbm2ddl.auto", "none");
//        hibernateProperties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
//        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        hibernateProperties.setProperty("hibernate.format_sql", "true");
//        hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
//        return hibernateProperties;
//    }

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

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        var factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setPackagesToScan("com.example.demo1");
//        factory.setJpaProperties(hibernateProperties());
//        factory.setDataSource(dataSource());
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return factory;
//    }
//
//    @Bean
//    public EntityManager entityManager() {
//        return entityManagerFactoryBean().createNativeEntityManager(null); // todo null?
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }

//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

}