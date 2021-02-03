package IONetwork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javafx.scene.web.WebView;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        String urlName = "http://google.com";
//        webEngine.load(urlName);
        URL url = new URL(urlName);
        URLConnection connection = url.openConnection();
        connection.connect();

        // print header fields
        Map<String, List<String>> headers =
                connection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue())
                System.out.println(key + ": " + value);
        }
        System.out.println("getContentEncoding: " + connection.getContentEncoding());
        String content = new String(connection.getInputStream().readAllBytes(), connection.getContentEncoding() != null ? connection.getContentEncoding() : "ASCII");

        webEngine.loadContent(content, "text/html");
        scene = new Scene(new BorderPane(webView));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}