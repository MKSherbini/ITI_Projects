module JavaGUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.rmi;

    opens JavaGUI to javafx.fxml;
    exports JavaGUI;
    exports JavaGUI.ChatingRMI;
}