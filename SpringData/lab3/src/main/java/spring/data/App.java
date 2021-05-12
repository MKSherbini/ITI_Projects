package spring.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTemplate;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;


@Configuration
//@EnableWebSecurity
//@EnableTransactionManagement
public class App {

//    @Bean(value = "dataSource", autowireCandidate = true)
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
//        dataSource.setUsername("admin");
//        dataSource.setPassword("admin");
//        return dataSource;
//    }
//
//    @Bean
//    public Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
////        hibernateProperties.setProperty(
////                "hbm2ddl.auto", "none");
//        hibernateProperties.setProperty(
//                "dialect", "org.hibernate.dialect.MySQL8Dialect");
//        return hibernateProperties;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        var factory = new LocalSessionFactoryBean();
//        factory.setDataSource(getDataSource());
////        factory.setMappingResources("");
//        factory.setPackagesToScan("spring.security");
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

//    @Bean
//    public LocalSessionFactoryBean getHibernateTemplate() {
//        var factory = new HibernateTemplate();
//        factory.setSessionFactory(getSessionFactory());
//        return factory;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(getDataSource());
////                .withDefaultSchema();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(User.withUsername("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities("ROLE_ADMIN"))
//                .withUser(User.withUsername("user")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities("ROLE_USER"));
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/app/admin**").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers("/app/user**").hasAnyAuthority("ROLE_USER")
//                .antMatchers("/").permitAll()
//                .and()
//                .rememberMe()
//                .rememberMeParameter("remember-me") // todo
//                .tokenValiditySeconds(99999)
//                .and()
//                .sessionManagement()
//                .maximumSessions(1)
////                .formLogin()
//                .and().and()
//                .formLogin()
//                .loginPage("/app/security/login")
//                .loginProcessingUrl("/app/security/submit")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll()
//                .and()
//                .logout()
//                .deleteCookies("spongebob")
//                .logoutSuccessUrl("/")
//                .permitAll()
//                .and()
//                .csrf().disable();
//
//    }


}

























