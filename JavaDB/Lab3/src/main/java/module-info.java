module JavaDB {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.materialdesign2;

    requires java.sql;
    requires mysql.connector.java;
    requires java.naming;
    requires java.sql.rowset;

    exports JavaDB;
//    opens JavaDB.ui.controllers;
}