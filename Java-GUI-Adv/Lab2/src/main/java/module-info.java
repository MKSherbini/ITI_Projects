module Lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.apache.commons.io;

    opens Lab2 to javafx.fxml;
    exports Lab2;
}