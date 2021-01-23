package Lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(stage.getWidth()); // 612 - 502
        stage.setMinHeight(stage.getHeight()); // 589 - 477
        stage.setHeight(600);
        stage.setWidth(600);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static TreeItem<BrowserItemModel> genBrowserTree(File dir, int depthLimit) {
        var dirList = dir.list();
        if (dirList == null || depthLimit == 0) return new TreeItem<>(new BrowserItemModel(dir));

        var tree = new TreeItem<>(new BrowserItemModel(dir));

        for (String s : dirList) {
            var item = new BrowserItemModel(dir, s);
            if (item.m_type == BrowserItemModel.TYPE.DIRECTORY) {
                tree.getChildren().add(genBrowserTree(item.getFileDir(), depthLimit - 1));
            } else {
                tree.getChildren().add(new TreeItem<>(item));
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        launch();
    }

}