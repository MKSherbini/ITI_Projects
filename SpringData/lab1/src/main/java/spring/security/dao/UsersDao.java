package spring.security.dao;

import spring.security.models.SystemUser;

import java.util.List;

public interface UsersDao {
    Integer count();

    int countByEnabled();

    int addUser(String username, String password, int enabled);

    List<SystemUser> getUsers();
}
