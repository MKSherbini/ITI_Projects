module JavaGUI {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.gluonhq.charm.glisten;

    opens JavaGUI to javafx.fxml;
    exports JavaGUI;
}