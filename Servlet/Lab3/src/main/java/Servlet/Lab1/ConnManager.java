package Servlet.Lab1;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnManager {
    public static ConnManager instance = new ConnManager();
    private final DataSource dataSource;

    private ConnManager() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/db");
            System.out.println("datasource " + dataSource);
        } catch (NamingException e) {
            // Handle error that it's not configured in JNDI.
            throw new IllegalStateException("missing JNDI!", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public Connection getConnection2() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chatapp", "mks", "jets");
    }
}
