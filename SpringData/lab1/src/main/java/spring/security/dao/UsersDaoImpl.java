package spring.security.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring.security.mappers.UsersMapper;
import spring.security.mappers.UsersRSExtractor;
import spring.security.models.SystemUser;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {
    private static final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);


//    private org.springframework.context.ApplicationContext appContext;
//
//    private UsersDao() {
//        logger.info("appContext.getBean(\"dataSource\") = " + appContext.getBean("dataSource"));
//        jdbcTemplate = new JdbcTemplate((DataSource) appContext.getBean("dataSource"));
//        logger.info("dataSource = " + jdbcTemplate);
//    }

    //    @Autowired
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        logger.info("dataSource = " + dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingColumns("username", "password", "enabled");
    }

    @Override
    public Integer count() {
//        if (jdbcTemplate == null) jdbcTemplate = new JdbcTemplate((DataSource) appContext.getBean("dataSource"));
        logger.info("jdbcTemplate = " + jdbcTemplate);
        if (jdbcTemplate == null) return -1;
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public int countByEnabled() {
        return jdbcTemplate.queryForObject("select count(*) from users where enabled>0", Integer.class, 1);
    }

    @Override
    public int addUser(String username, String password, int enabled) {
//        return jdbcTemplate.update(
//                "INSERT INTO users VALUES (?, ?, ?)", username, password, enabled);
//        return namedParameterJdbcTemplate.update("INSERT INTO users VALUES (:u, :p, :e)",
//                Map.of("u", username, "p", password, "e", enabled));
        return jdbcInsert.execute(Map.of("username", username, "password", password, "enabled", enabled));
    }

    @Override
    public List<SystemUser> getUsers() {
//        return jdbcTemplate.query("select * from users", new UsersRSExtractor());
        return jdbcTemplate.query("select * from users", new UsersMapper());
    }

}
