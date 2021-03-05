package Servlet.Lab1;

import javax.sql.RowSet;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    public static DBManager instance = new DBManager();

    private DBManager() {
    }

    public int insertContactRecord(User user) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            con = ConnManager.instance.getConnection();
            String insertQuery = "insert into user values (?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.firstName);
            preparedStatement.setString(2, user.lastName);
            preparedStatement.setString(3, user.username);
            preparedStatement.setString(4, user.password);
            rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (con != null)
                    con.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return rowsAffected;
    }

    public ArrayList<User> getContactsList(String userId) {
        ArrayList<User> contactsList = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        try {
            con = ConnManager.instance.getConnection();
            String query = "select * from user where firstName like ? or lastName like ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "%" + userId + "%");
            preparedStatement.setString(2, "%" + userId + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.firstName = (rs.getString(1));
                user.lastName = (rs.getString(2));
                user.username = (rs.getString(3));
                user.password = (rs.getString(4));
                contactsList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (con != null)
                    con.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return contactsList;
    }

    public User selectFromDB(String userName, String password) throws RemoteException {
        User user = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnManager.instance.getConnection2();
            String sql = "select * from user where username = ? and password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.firstName = (rs.getString(1));
                user.lastName = (rs.getString(2));
                user.username = (rs.getString(3));
                user.password = (rs.getString(4));
                return user;
            }
        } catch (SQLException | NullPointerException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    public String query(String query) throws SQLException {
        ArrayList<User> contactsList = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String res = "";
        try {
            con = ConnManager.instance.getConnection();
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            res = HtmlConverter.instance.convertToTable(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("Illegal query");
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (con != null)
                    con.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return res;
    }

    public int update(String query) throws SQLException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            con = ConnManager.instance.getConnection();
            preparedStatement = con.prepareStatement(query);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new SQLException("Illegal query");
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (con != null)
                    con.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return rowsAffected;
    }

}
