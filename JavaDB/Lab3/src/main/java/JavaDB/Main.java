package JavaDB;

import JavaDB.ui.helpers.StageCoordinator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.sql.rowset.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.RowSetProvider;

import java.sql.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
//        stageCoordinator.initStage(primaryStage);
//        stageCoordinator.switchToPrimaryScene();
//        primaryStage.show();


//        Lab1();
//        Lab2();
//        Lab3();
//        Lab4();

        Platform.exit();
    }

    private void Lab4() {
        RowSetFactory aFactory = null;
        try {
            aFactory = RowSetProvider.newFactory();
            WebRowSet rs = aFactory.createWebRowSet();
            rs.setCommand("select * from actor");
            rs.setUrl("jdbc:mysql://localhost:3306/sakila");
            rs.setUsername("mks");
            rs.setPassword("jets");
            rs.execute();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
//            rs.writeXml(System.out);
            rs.writeXml(new FileWriter("webrowset.xml"));
            rs.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    private void Lab3() {
        try {
            RowSetFactory rsFactory = null;
            rsFactory = RowSetProvider.newFactory();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila"
                    , "mks", "jets");

            CachedRowSet rs = rsFactory.createCachedRowSet();
            rs.setCommand("select * from city");
            rs.execute(con);

            CachedRowSet rs2 = rsFactory.createCachedRowSet();
            rs2.setCommand("select * from country");
            rs2.execute(con);

            JoinRowSet jrs = rsFactory.createJoinRowSet();
            System.out.println("joined city with country");
            rs.setMatchColumn("country_id");
            jrs.addRowSet(rs);
            jrs.addRowSet(rs2, "country_id");

            ResultSetMetaData rsmd = jrs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (jrs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = jrs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }

            rs.close();
            rs.close();
            jrs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void Lab2() {
        RowSetFactory aFactory = null;
        try {
            aFactory = RowSetProvider.newFactory();
            CachedRowSet jrs = aFactory.createCachedRowSet();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jets"
                    , "mks", "jets");
            con.setAutoCommit(false);
            jrs.setCommand("select * from employee2");
            jrs.execute(con);
            ResultSetMetaData rsmd = jrs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (jrs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = jrs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                jrs.updateInt("Vacation_Balance", jrs.getInt("Vacation_Balance") + 1);
                jrs.updateRow();
                System.out.println("");
            }
            jrs.acceptChanges();

            jrs.beforeFirst();
            System.out.println("Contents of the table after updating the row");
            while (jrs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = jrs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
            jrs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void Lab1() {
        RowSetFactory aFactory = null;
        try {
            aFactory = RowSetProvider.newFactory();
            JdbcRowSet jrs = aFactory.createJdbcRowSet();
            jrs.setCommand("select * from actor");
            jrs.setUrl("jdbc:mysql://localhost:3306/sakila");
            jrs.setUsername("mks");
            jrs.setPassword("jets");
            jrs.execute();
            ResultSetMetaData rsmd = jrs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (jrs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = jrs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
            jrs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
