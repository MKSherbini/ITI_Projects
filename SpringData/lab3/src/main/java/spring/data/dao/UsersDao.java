package spring.data.dao;

import spring.data.models.SystemUser;

import java.util.List;

public interface UsersDao {
    Long count();

    int countByEnabled();

    int addUser(String username, String password, int enabled);

    List<SystemUser> getUsers();

    void deleteUser(String username);
}
