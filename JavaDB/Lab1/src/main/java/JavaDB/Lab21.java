package JavaDB;

import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static JavaDB.Lab11.loadDSFile;

public class Lab21 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try (Connection con = loadDSFile().getConnection();) {
            PreparedStatement pstmt = con.prepareStatement("DROP TABLE Employee2");
            pstmt.executeUpdate();
            pstmt = con.prepareStatement("create table Employee2 (ID SMALLINT ," +
                    "F_Name varchar(50),L_Name varchar(50),Sex varchar(50)," +
                    "Age SMALLINT ,Address varchar(50),Phone_Number varchar(50)," +
                    "Vacation_Balance smallint default 30)");
            pstmt.executeUpdate();

            pstmt = con.prepareStatement("insert into Employee2 values (?,?,?,?,?,?,?,30)");

            for (int i = 0; i < 5; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "fname" + i);
                pstmt.setString(3, "lname" + i);
                pstmt.setString(4, i % 2 == 0 ? "MALE" : "FEMALE");
                pstmt.setInt(5, i * 15);
                pstmt.setString(6, "address" + i);
                pstmt.setString(7, "phone" + i);
                pstmt.execute();
            }
            pstmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Platform.exit();
    }


}
