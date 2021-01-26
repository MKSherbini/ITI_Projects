package JavaDB;

import JavaDB.ui.helpers.StageCoordinator;
import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        Lab12(primaryStage);

//        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "mks", "jets");
//             Statement stmt = con.createStatement()) {
//
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            String queryString = new String("select * from actor");
//            ResultSet rs = stmt.executeQuery(queryString);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//
//            while (rs.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) System.out.print(",  ");
//                    String columnValue = rs.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        Platform.exit();
    }



    private void Lab12(Stage primaryStage) {
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToLab1Scene();
        primaryStage.show();
    }


    @Override
    public void init() {
        // Initialize Database & Network Connections
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
    }

}




/*
      gui te3gebo
    x deactivate btns
    x better msgs
    x validation
 */