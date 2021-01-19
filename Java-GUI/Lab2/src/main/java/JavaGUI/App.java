package JavaGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
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
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // create stops
        Stop[] stops = {new Stop(0, Color.BLACK),
                new Stop(0.5, Color.WHITE),
                new Stop(1, Color.BLACK)};
        LinearGradient linear_gradient = new LinearGradient(0, 0,
                0, 1, true, CycleMethod.NO_CYCLE, stops);

        var pane = new StackPane();

        var label = new Label("Hello World");
        Reflection r = new Reflection();
        label.setTextFill(Color.RED);
        label.setFont(Font.font("null", FontWeight.BOLD, 30));
        r.setFraction(0.9);
        label.setEffect(r);

        Rectangle r1 = new Rectangle(0, 0, 640, 480);
        r1.setFill(linear_gradient);
        pane.getChildren().add(r1);
        pane.getChildren().add(label);
        var scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}