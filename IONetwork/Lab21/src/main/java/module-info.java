module JavaGUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens JavaGUI to javafx.fxml;
    exports JavaGUI;
}