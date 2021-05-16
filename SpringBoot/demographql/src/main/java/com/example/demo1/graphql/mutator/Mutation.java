package com.example.demo1.graphql.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo1.dao.UsersDao;
import com.example.demo1.graphql.exceptions.ResourceNotFoundException;
import com.example.demo1.models.SystemUser;
//import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
    private UsersDao usersDao;

    @Autowired //  usersDao
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public Iterable<SystemUser> addUser(String username, String password, Boolean enabled) {
        usersDao.addUser(username, password, enabled);
        return usersDao.findAll();
    }

    public Iterable<SystemUser> deleteUser(String username) {
        if (usersDao.existsById(username))
            usersDao.deleteById(username);
        else
            throw new ResourceNotFoundException("id not found", username);

        return usersDao.findAll();
    }
}
