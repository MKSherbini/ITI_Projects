package spring.data.mappers;

import org.springframework.jdbc.core.RowMapper;
import spring.data.models.SystemUser;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersMapper implements RowMapper<SystemUser> {

    @Override
    public SystemUser mapRow(ResultSet resultSet, int i) throws SQLException {

        return new SystemUser(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
    }
}
