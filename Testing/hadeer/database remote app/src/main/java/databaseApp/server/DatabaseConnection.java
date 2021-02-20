package databaseApp.server;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private Connection con =null;
    private InputStream inputStream;
    private Properties properties=new Properties();
    public DatabaseConnection(){
        try {
            inputStream=new FileInputStream("db.properties");
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            properties.load(inputStream);
            mysqlDataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
            mysqlDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
            con=mysqlDataSource.getConnection();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return con;
    }
}
