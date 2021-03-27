package providers.database.impl;

import providers.database.Database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlDatabase extends Database {
    private final DataSource dataSource;

    public SqlDatabase() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/db");
        } catch (NamingException e) {
            // Handle error that it's not configured in JNDI.
            throw new IllegalStateException("missing JNDI!", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
