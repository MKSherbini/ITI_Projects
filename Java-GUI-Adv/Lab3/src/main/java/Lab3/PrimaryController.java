package Lab3;

import java.io.File;
import java.io.SyncFailedException;
import java.net.URL;
import java.util.*;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;
import javafx.util.Duration;

public class PrimaryController implements Initializable {


    public SVGPath svgPath;
    public Rectangle rectangle;
    public AnchorPane anchorPane;
    public javafx.scene.image.ImageView imageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File resource = App.class.getResource("sponge.wav");
//        AudioClip clip = new AudioClip(resource.toURI().toString());
        AudioClip clip = new AudioClip(App.class.getResource("sponge.wav").toString());
        clip.setCycleCount(Timeline.INDEFINITE);
        new Thread(clip::play).start();


//        Runnable r = () -> {
//            imageView = new ImageView(new Image("Lab3/background.jpg"));
//            imageView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
//            imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//        new Thread(r).start();

//        imageView.fitWidthProperty().bindBidirectional(Screen.);

//        rectangle.layoutXProperty().bind(new DoubleBinding() {
//            {
//                super.bind(svgPath.layoutXProperty());
//            }
//
//            @Override
//            protected double computeValue() {
//                return svgPath.layoutXProperty().get();
//            }
//        });
//        rectangle.layoutYProperty().bind(new DoubleBinding() {
//            {
//                super.bind(svgPath.layoutYProperty());
//            }
//
//            @Override
//            protected double computeValue() {
//                return svgPath.layoutYProperty().get() * svgPath.getScaleY();
//            }
//        });
//        rectangle.layoutXProperty().bind(svgPath.layoutXProperty());
//        rectangle.layoutYProperty().bind(svgPath.layoutYProperty());


//        ChangeListener windowResizeListener = (observable, oldValue, newValue) -> {
//
//
//        };
//        svgPath.layoutXProperty().addListener(windowResizeListener);

//        svgPath.setFill(Color.TRANSPARENT);
//        svgPath.setStrokeWidth(1.0);
//        svgPath.setStroke(Color.BLACK);
//        svgPath.setContent("M 787.49,150 C 787.49,203.36 755.56,247.27 712.27,269.5 S 622.17,290.34 582.67,279.16 508.78,246.56 480,223.91 424.93,174.93 400,150 348.85,98.79 320,76.09 256.91,32.03 217.33,20.84 130.62,8.48 87.73,30.5 12.51,96.64 12.51,150 44.44,247.27 87.73,269.5 177.83,290.34 217.33,279.16 291.22,246.56 320,223.91 375.07,174.93 400,150 451.15,98.79 480,76.09 543.09,32.03 582.67,20.84 669.38,8.48 712.27,30.5 787.49,96.64 787.49,150 z");
//        svgPath.setContent("M380.52.5h152s66,6,86.67,85.33-78,114.67-78,114.67H380.52L238.52.5h-154S-.82,19.17.52,97.5s71.33,103.67,79.33,103,157.33,0,157.33,0Z");

//        badPT = doSMT(rectangle, 10_000);
//        var r = new Rectangle(30, 30);
//        anchorPane.getChildren().add(r);
//        doSMT(r, 12_000);
//        rects.add(r);
//
//        r = new Rectangle(30, 30, Color.BLUE);
//        anchorPane.getChildren().add(r);
//        doSMT(r, 10_000);
//        rects.add(r);
        addImage("Lab3/sponge.png", 10_000, -1);
        addImage("Lab3/sponge2.png", 12_000);
        addImage("Lab3/sponge3.png", 14_000, -1);
        addImage("Lab3/sponge4.png", 16_000);
        addImage("Lab3/sponge5.png", 13_000);
        addImage("Lab3/sponge6.png", 11_000);
    }

    void addImage(String path, double dur) {
        addImage(path, dur, 1);
    }

    void addImage(String path, double dur, int rate) {
        var img = new Image(path, 55, 55, false, true);

//        var r = new Rectangle();
//        r.setFill(new ImagePattern(img));
        var r = new ImageView(img);
        anchorPane.getChildren().add(r);
        doSMT(r, dur, rate);
    }

    //    List<PathTransition> pts = new ArrayList<>();
    List<Node> racers = new ArrayList<>();
    List<Long> racersCol = new ArrayList<>();
    Long colThreshold = 1000L;
    PathTransition badPT;
    List<SimpleDoubleProperty> binds = new ArrayList<>();

    private void doSMT(Node target, double duration, int rate) {
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(duration));
        pt.setPath(svgPath);
        pt.setNode(target);
        pt.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setInterpolator(Interpolator.LINEAR);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setRate(rate);
        pt.play();

//        target.layoutXProperty().bindBidirectional(svgPath.layoutXProperty());
//        target.layoutYProperty().bindBidirectional(svgPath.layoutYProperty());
//        svgPath.layoutXProperty().bind(target.layoutXProperty());
//        target.layoutYProperty().bind(svgPath.layoutYProperty());

        bindIndirect(target.layoutXProperty(), svgPath.layoutXProperty());
        bindIndirect(target.layoutYProperty(), svgPath.layoutYProperty());

        Runnable reverse = () -> {
            target.setScaleX(-1 * target.getScaleX());
            pt.setRate(-1 * pt.getRate());
        };
        // padding
        target.setOnMousePressed((e) -> {
            reverse.run();
        });

        Runnable eventReg = () -> {
            target.translateXProperty().addListener(((observableValue, oldX, newX) -> {
                System.out.println("BoundsListener");
                int i = 0;
                var now = new Date().getTime();
                for (Node racer : racers) {
                    var b = target.getBoundsInParent();
                    var b2 = racer.getBoundsInParent();
                    if (b != b2 && b.intersects(b2) && (now - racersCol.get(i) > colThreshold)) {
                        reverse.run();
                        racersCol.set(i, new Date().getTime());
                    }
                    i++;
                }
            }));
//            target.translateYProperty().addListener(((observableValue, oldX, newX) -> {
//                System.out.println("BoundsListener");
//                for (Node racer : racers) {
//                    var b = target.getBoundsInParent();
//                    var b2 = racer.getBoundsInParent();
//                    if (b != b2 && b.intersects(b2)) {
//                        reverse.run();
//                    }
//                }
//            }));
        };
        eventReg.run();
//        DelayedRun(eventReg, 1000);

        racers.add(target);
        racersCol.add(new Date().getTime());
        //        pts.add(pt);
    }

    private void bindIndirect(DoubleProperty updateTarget, DoubleProperty updatedBy) {
        var temp = new SimpleDoubleProperty();
        updateTarget.bindBidirectional(temp);
        temp.bind(updatedBy);
        binds.add(temp);
    }

    void bindIndirect() {

    }

    public void onRectClicked(MouseEvent mouseEvent) {
        badPT.setAutoReverse(true);
        System.out.println("rectangle.setOnMouseClicked");
    }

    void DelayedRun(Runnable r, int delay) {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                r.run();
            }
        }, delay);
    }
}
