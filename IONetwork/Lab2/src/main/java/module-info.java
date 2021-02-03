module IONetwork {
    requires javafx.controls;
    requires javafx.fxml;


    opens IONetwork to javafx.fxml;
    exports IONetwork;
}