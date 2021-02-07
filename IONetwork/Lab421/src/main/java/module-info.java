module JavaGUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.rmi;

    opens JavaGUI.Controller to javafx.fxml;
    exports JavaGUI;
    exports JavaGUI.ChatingRMI.Client;
    exports JavaGUI.ChatingRMI.Server;
    exports JavaGUI.ChatingRMI.Common;
    exports JavaGUI.Controller;
}