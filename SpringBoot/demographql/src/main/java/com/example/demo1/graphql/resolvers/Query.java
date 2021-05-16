package com.example.demo1.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo1.dao.UsersDao;
import com.example.demo1.models.SystemUser;
//import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    private UsersDao usersDao;

    @Autowired //  usersDao
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public Iterable<SystemUser> findAll() {
        return usersDao.findAll();
    }

    public long count() {
        return usersDao.count();
    }
}
