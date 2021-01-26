package JavaDB.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.*;

import static JavaDB.Lab11.loadDSFile;

public class Lab1Controller implements Initializable {


    public TextField txt_phone;
    public TextField txt_email;
    public TextField txt_lname;
    public TextField txt_mname;
    public TextField txt_fname;
    public TextField txt_id;
    public Button btn_new;
    public Button btn_update;
    public Button btn_delete;
    public Button btn_first;
    public Button btn_prev;
    public Button btn_next;
    public Button btn_last;

    Connection con;
    Statement stmt;
    ResultSet rs;
    Set<Integer> idsSet = new TreeSet<>();

    String emailRgx = "^[A-Za-z0-9._-]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}$";
    String phoneRgx = "^^(\\+2)?\\d{11}$";
    private int maxNameLength = 10;
    private int minNameLength = 1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_id.setDisable(true);
        try {
            con = loadDSFile().getConnection();
            stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            updateRS();
            enableDisableBtns();
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);
        }
    }

    void updateRS() throws SQLException {
        int currIdxPos = -1;
        int currIdxVal = -1;
        if (rs != null && !rs.isClosed()) {
            currIdxVal = rs.getInt(1);
            rs.close();
        }

        String queryString = new String("select * from employee");
        rs = stmt.executeQuery(queryString);
        for (int i = 1; rs.next(); i++) {
            if (rs.getInt(1) == currIdxVal)
                currIdxPos = i;
            idsSet.add(rs.getInt(1));
        }
        if (currIdxPos == -1)
            onClickFirst(null);
        else
            rs.absolute(currIdxPos);
    }

    public void onClickNew(ActionEvent actionEvent) {
        try {
            if (btn_new.getText().equals("New")) {
                updateNewbtn(true);
                return;
            }
            if (!validFields()) {
//                updateNewbtn(false);
//                setTextFields();
                return;
            }
            rs.moveToInsertRow();
            rsUpdateRowFromGui();
            rs.insertRow();
//            rs.moveToCurrentRow();
//            rs.next(); // todo is this rly necessary
            updateNewbtn(false);
            updateRS();
            enableDisableBtns();
            displayInfoAlert("New row inserted");
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);
        }
    }

    void updateNewbtn(boolean state) {
        if (state) {
            btn_new.setText("Insert");
            txt_id.setDisable(false);
            btn_update.setDisable(true);
            btn_delete.setDisable(true);
            btn_first.setDisable(true);
            btn_prev.setDisable(true);
            btn_next.setDisable(true);
            btn_last.setDisable(true);
        } else {
            btn_new.setText("New");
            txt_id.setDisable(true);
            btn_new.setDisable(false);
            btn_update.setDisable(false);
            btn_delete.setDisable(false);
            btn_first.setDisable(false);
            btn_prev.setDisable(false);
            btn_next.setDisable(false);
            btn_last.setDisable(false);
        }
    }

    public void onClickUpdate(ActionEvent actionEvent) {
        try {
            if (!validFields(false)) {
                return;
            }
            rsUpdateRowFromGui();
            rs.updateRow();
            enableDisableBtns();
            displayInfoAlert("Row updated");
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);
        }
    }

    void rsUpdateRowFromGui() throws SQLException {
        rs.updateString("Phone_Number", txt_phone.getText());
        rs.updateString("Email", txt_email.getText());
        rs.updateString("L_Name", txt_lname.getText());
        rs.updateString("M_Name", txt_mname.getText());
        rs.updateString("F_Name", txt_fname.getText());
        rs.updateString("ID", txt_id.getText());
    }

    public void onClickDelete(ActionEvent actionEvent) {
        try {
            idsSet.remove(rs.getInt(1));
            rs.deleteRow();
            setTextFields();
            enableDisableBtns();
            displayInfoAlert("Row deleted");
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);
        }
    }

    public void onClickFirst(ActionEvent actionEvent) {
        try {
            rs.first();
            setTextFields();
            enableDisableBtns();
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);
        }
    }

    public void onClickPrev(ActionEvent actionEvent) {
        try {
            rs.previous();
            setTextFields();
            enableDisableBtns();
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);

        }
    }

    public void onClickNext(ActionEvent actionEvent) {
        try {
            rs.next();
            setTextFields();
            enableDisableBtns();
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);

        }
    }

    public void onClickLast(ActionEvent actionEvent) {
        try {
            rs.last();
            setTextFields();
            enableDisableBtns();
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);
        }
    }

    void enableDisableBtns() throws SQLException {
        btn_next.setDisable(rs.isLast() || rs.isAfterLast() || rs.isBeforeFirst());
        btn_last.setDisable(rs.isLast() || rs.isAfterLast() || rs.isBeforeFirst());
        btn_prev.setDisable(rs.isFirst() || rs.isAfterLast() || rs.isBeforeFirst());
        btn_first.setDisable(rs.isFirst() || rs.isAfterLast() || rs.isBeforeFirst());
        btn_delete.setDisable(rs.isAfterLast() || rs.isBeforeFirst());
        btn_update.setDisable(rs.isAfterLast() || rs.isBeforeFirst());
    }

    public void displayErrorAlert(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred");
        alert.setContentText(e.getMessage());
        alert.show();
    }

    public void displayErrorAlert(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred");
        alert.setContentText(e);
        alert.showAndWait();
    }

    public void displayInfoAlert(String e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Success");
        alert.setContentText(e);
        alert.showAndWait();
    }

    boolean validFields() {
        return validFields(true);
    }

    boolean validFields(boolean validateId) {
        if (validateId) {

            int id = -1;
            boolean invalidIdString = false;
            try {
                id = Integer.parseInt(txt_id.getText());
            } catch (NumberFormatException e) {
                invalidIdString = true;
            }
            if (id <= 0 || invalidIdString) {
                displayErrorAlert("Invalid number for ID");
            }

            if (idsSet.contains(id)) {
                invalidIdString = true;
                displayErrorAlert("ID was used before, can't use it again");
            }
            if (invalidIdString) {
                Dialog<ButtonType> d = new Dialog<>();
                d.setTitle("Kind Programmer Choice");
                d.setContentText("Invalid ID, use autoincrement instead?");
                d.getDialogPane().getButtonTypes().add(ButtonType.YES);
                d.getDialogPane().getButtonTypes().add(ButtonType.NO);
                Optional<ButtonType> res = d.showAndWait();
                if (res.isPresent()) {
                    if (res.get() == ButtonType.YES) {
                        txt_id.setText(String.valueOf(idsSet.stream().max(Integer::compareTo).get() + 1));
                    } else if (res.get() == ButtonType.NO) {
                        return false;
                    }
                }
            }
        }


        int len = Math.max(txt_fname.getText().length(), txt_lname.getText().length());
        len = Math.max(len, txt_mname.getText().length());
        if (len > maxNameLength) {
            displayErrorAlert("Max name fields length is " + maxNameLength + " Chars");
            return false;
        }

        len = Math.min(txt_fname.getText().length(), txt_lname.getText().length());
        len = Math.min(len, txt_mname.getText().length());
        if (len < minNameLength) {
            displayErrorAlert("Min name fields length is " + minNameLength + " Chars");
            return false;
        }

        if (!txt_email.getText().matches(emailRgx)) {
            displayErrorAlert("Bad Email Format");
            return false;
        }

        if (!txt_phone.getText().matches(phoneRgx)) {
            displayErrorAlert("Bad Phone Format");
            return false;
        }
        return true;
    }

    private void clearTextFields() {
        txt_phone.clear();
        txt_email.clear();
        txt_lname.clear();
        txt_mname.clear();
        txt_fname.clear();
        txt_id.clear();
    }

    private void setTextFields() {
        try {
            if (rs.isBeforeFirst())
                rs.first();
            txt_phone.setText(rs.getString("Phone_Number"));
            txt_email.setText(rs.getString("Email"));
            txt_lname.setText(rs.getString("L_Name"));
            txt_mname.setText(rs.getString("M_Name"));
            txt_fname.setText(rs.getString("F_Name"));
            txt_id.setText(rs.getString("ID"));
        } catch (SQLException throwables) {
            displayErrorAlert(throwables);
        }
    }

    public void tester(ActionEvent actionEvent) {
        displayErrorAlert("omg");
    }
}
