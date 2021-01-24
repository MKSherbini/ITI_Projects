module Lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;
    requires batik.util;
    requires batik.parser;
    requires javafx.media;

    opens Lab3 to javafx.fxml;
    exports Lab3;
}