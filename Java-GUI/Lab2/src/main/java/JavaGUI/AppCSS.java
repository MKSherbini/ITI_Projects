package JavaGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class AppCSS extends Application {

    @Override
    public void start(Stage stage) {
//        System.err.println(getClass().getClassLoader().getResource("test.css"));

        var pane = new StackPane();
        var scene = new Scene(pane, 640, 480);
        scene.getStylesheets().add("test.css");

        var label = new Label("Hello World");
        label.getStyleClass().add("label");

        var r1 = new Region();
//        Rectangle r1 = new Rectangle(0, 0, 640, 480);
        r1.getStyleClass().add("rect");

        pane.getChildren().add(r1);
        pane.getChildren().add(label);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}