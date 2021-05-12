package spring.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.data.dao.UsersDao;

@Service
public class SystemUserService {

    private UsersDao usersDao;

    @Autowired //  usersDao
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


}
