module javafxModule {
    exports menuBarUsingSAX;
    opens menuBarUsingSAX to javafx.fxml;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.materialdesign2;
    requires java.xml;

}
