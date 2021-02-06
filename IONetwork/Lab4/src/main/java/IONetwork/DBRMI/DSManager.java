package IONetwork.DBRMI;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class DSManager {
    public static void main(String[] args) {
        createFile();
    }

    private static void createFile() {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("db.properties");
            prop.setProperty("MYSQL_DB_URL", "jdbc:mysql://localhost:3306/jets");
            prop.setProperty("MYSQL_DB_USERNAME", "mks");
            prop.setProperty("MYSQL_DB_PASSWORD", "jets");
            prop.store(output, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MysqlDataSource loadDSFile() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = null;

        try {
            fis = new FileInputStream("db.properties");
            props.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
}
