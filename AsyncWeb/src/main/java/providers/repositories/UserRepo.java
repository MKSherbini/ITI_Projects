package providers.repositories;

import managers.DatabaseManager;
import models.User;
import providers.database.DatabaseFactory;
import providers.database.QueryResultRow;
import providers.database.SqlCommand;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private static volatile UserRepo instance = null;

    private UserRepo() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
    }

    public static UserRepo getInstance() {
        if (instance == null) {
            synchronized (UserRepo.class) {
                if (instance == null) {
                    instance = new UserRepo();
                }
            }
        }
        return instance;
    }

    public int insertContactRecord(User user) {
        String insertQuery = "insert into user values (?, ?, ?, ?)";
        SqlCommand cmd = new SqlCommand(insertQuery
                , user.getFirstName()
                , user.getLastName()
                , user.getUserName()
                , user.getPassword());
        return DatabaseManager.getInstance().getDatabaseInstance().executeUpdate(cmd);
    }

    public List<User> searchContacts(String name) {
        ArrayList<User> contactsList = new ArrayList<>();
        String sql = "select * from user where firstName like ? or lastName like ?";
        SqlCommand cmd = new SqlCommand(sql,
                "%" + name + "%",
                "%" + name + "%");
        var result = DatabaseManager.getInstance().getDatabaseInstance().executeQuery(cmd);
        for (QueryResultRow rs : result) {
            User user = new User();
            user.setFirstName(rs.getString(1));
            user.setLastName(rs.getString(2));
            user.setUserName(rs.getString(3));
            user.setPassword(rs.getString(4));
            contactsList.add(user);
        }
        return contactsList;
    }

    public User selectFromDB(String userName, String password) throws RemoteException {
        User user = null;
        String sql = "select * from user where username = ? and password = ?";
        SqlCommand cmd = new SqlCommand(sql,
                userName,
                password);
        var result = DatabaseManager.getInstance().getDatabaseInstance().executeQuery(cmd);
        if (result.size() > 0) {
            var rs = result.get(0);
            user = new User();
            user.setFirstName(rs.getString(1));
            user.setLastName(rs.getString(2));
            user.setUserName(rs.getString(3));
            user.setPassword(rs.getString(4));
        }
        return user;
    }

    public User selectFromDB(String userName) throws RemoteException {
        User user = null;
        String sql = "select * from user where username = ?";
        SqlCommand cmd = new SqlCommand(sql,
                userName);
        var result = DatabaseManager.getInstance().getDatabaseInstance().executeQuery(cmd);
        if (result.size() > 0) {
            var rs = result.get(0);
            user = new User();
            user.setFirstName(rs.getString(1));
            user.setLastName(rs.getString(2));
            user.setUserName(rs.getString(3));
            user.setPassword(rs.getString(4));
        }
        return user;
    }
}
