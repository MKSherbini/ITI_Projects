package spring.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
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

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        var factory = new LocalSessionFactoryBean();
        factory.setDataSource(getDataSource());
//        factory.setMappingResources("");
        factory.setPackagesToScan("spring.data");
        factory.setHibernateProperties(hibernateProperties());
        return factory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        var factory = new HibernateTemplate();
        factory.setSessionFactory(sessionFactory().getObject());
        return factory;
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        var factory = new TransactionTemplate();
        factory.setTransactionManager(hibernateTransactionManager());
        return factory;
    }
}