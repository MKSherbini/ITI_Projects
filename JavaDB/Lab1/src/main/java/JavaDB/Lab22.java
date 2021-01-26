package JavaDB;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static JavaDB.Lab11.loadDSFile;

public class Lab22 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
//        try (Connection con = loadDSFile().getConnection();) {
//            con.setAutoCommit(false);
//
//            PreparedStatement pstmt = con.prepareStatement("select * from  Employee2");
//            var rs = pstmt.executeQuery();
//            pstmt = con.prepareStatement("update Employee2 " +
//                    "set F_Name = ?, Vacation_Balance=45 " +
//                    "where ID=?");
//
//            while (rs.next()) {
//                if (rs.getInt("Age") >= 45) {
//                    if (rs.getString("Sex").equals("MALE") && !rs.getString("F_Name").startsWith("Mr. "))
//                        pstmt.setString(1, "Mr. " + rs.getString("F_Name"));
//                    else
//                        pstmt.setString(1, "Mrs. " + rs.getString("F_Name"));
//                    pstmt.setInt(2, rs.getInt("ID"));
//                    pstmt.addBatch();
//                }
//            }
//
//            pstmt.executeBatch();
//            pstmt.close();
//            con.commit();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        String patern = "^[A-Za-z0-9._-]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}$";
        System.out.println("mks@gm.com".matches(patern));
        Platform.exit();
    }


}
