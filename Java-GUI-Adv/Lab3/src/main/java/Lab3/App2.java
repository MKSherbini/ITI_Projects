package Lab3;


import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App2 extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        AnchorPane pane = new AnchorPane();
////        Pane pane2 = new Pane();
////        pane.getChildren().add(pane2);
////        AnchorPane.setBottomAnchor(pane, 0.0);
////        AnchorPane.setLeftAnchor(pane, 0.0);
////        AnchorPane.setRightAnchor(pane, 0.0);
////        AnchorPane.setTopAnchor(pane, 0.0);
//
//        Rectangle rectangle = new Rectangle(0, 0, 25, 50);
//        rectangle.setFill(Color.ORANGE);
//
////        Circle circle = new Circle(125, 100, 50);
////        circle.setFill(Color.WHITE);
////        circle.setStroke(Color.BLACK);
//        SVGPath svg = new SVGPath();
//        svg.setFill(Color.TRANSPARENT);
//        svg.setStrokeWidth(1.0);
//        svg.setStroke(Color.BLACK);
//        svg.setContent("M 787.49,150 C 787.49,203.36 755.56,247.27 712.27,269.5 S 622.17,290.34 582.67,279.16 508.78,246.56 480,223.91 424.93,174.93 400,150 348.85,98.79 320,76.09 256.91,32.03 217.33,20.84 130.62,8.48 87.73,30.5 12.51,96.64 12.51,150 44.44,247.27 87.73,269.5 177.83,290.34 217.33,279.16 291.22,246.56 320,223.91 375.07,174.93 400,150 451.15,98.79 480,76.09 543.09,32.03 582.67,20.84 669.38,8.48 712.27,30.5 787.49,96.64 787.49,150 z");
//
////        pane.getChildren().add(circle);
//        pane.getChildren().add(svg);
//        pane.getChildren().add(rectangle);
//
//        anchorAll(pane);
//        anchorAll(svg);
//        anchorAll(rectangle);
//
//
////        pane1.getChildren().add(svg);
////        pane1.getChildren().add(rectangle);
//
//        PathTransition pt = new PathTransition();
//        pt.setDuration(Duration.millis(4000));
////        pt.setPath(circle);
//        pt.setPath(svg);
//        pt.setNode(rectangle);
//        pt.setOrientation(
//                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pt.setCycleCount(Timeline.INDEFINITE);
////        pt.setAutoReverse(true);
//        pt.play();
//
////        circle.setOnMousePressed(e -> pt.pause());
////        circle.setOnMouseReleased(e -> pt.play());
//
//        Scene scene = new Scene(pane);
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
//        Parent item = fxmlLoader.load();
//        PrimaryController ctrl = fxmlLoader.getController();
//        var rectangle = ctrl.rectangle;
//        var svgPath = ctrl.svgPath;
//        scene = new Scene(item);
        scene = new Scene(loadFXML("primary"));
//        Parent parent = loadFXML("primary");
//        scene = new Scene(parent, 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(368);
        stage.setMinWidth(857);
//        rectangle.layoutXProperty().bind(svgPath.layoutXProperty());
//        rectangle.layoutYProperty().bind(svgPath.layoutYProperty());
    }

    private void anchorAll(Node svg) {
        AnchorPane.setBottomAnchor(svg, 0.0);
        AnchorPane.setLeftAnchor(svg, 0.0);
        AnchorPane.setRightAnchor(svg, 0.0);
        AnchorPane.setTopAnchor(svg, 0.0);
    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}