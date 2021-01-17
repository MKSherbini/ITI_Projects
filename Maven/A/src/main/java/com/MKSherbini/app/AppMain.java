package com.MKSherbini.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("Hello, JavaFX !!!");

        StackPane pane = new StackPane();
        pane.getChildren().addAll(label);

        Scene scene = new Scene(pane, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World !!!");
        primaryStage.show();
    }

}
