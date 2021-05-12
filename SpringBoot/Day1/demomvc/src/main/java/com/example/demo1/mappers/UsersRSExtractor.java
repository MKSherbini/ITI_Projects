package com.example.demo1.mappers;

import com.example.demo1.models.SystemUser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRSExtractor implements ResultSetExtractor<List<SystemUser>> {
    @Override
    public List<SystemUser> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<SystemUser> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new SystemUser(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
        }
        return users;
    }
}
