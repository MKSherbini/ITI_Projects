module IONetwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens IONetwork to javafx.fxml;
    exports IONetwork;
}