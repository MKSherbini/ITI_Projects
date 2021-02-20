package menuBarUsingSAX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

public class HelloFX extends Application {
    Document document;
    Menu menu;
    MenuBar menubar;
    MenuItem menuItem;

    @Override
    public void start(Stage stage) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            CallBackHandler callBackHandler = new CallBackHandler();
            parser.parse(new File("C:\\Users\\Hadeer\\Desktop\\iti\\java xml api\\menubarUsingSAX\\src\\main\\resources\\menu.xml"), callBackHandler);
            MenuBar menuBar = callBackHandler.getMenuBar();
            Scene scene = new Scene(new AnchorPane(menuBar), 400, 400);
            stage.setScene(scene);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    private Document createDom() {

        return null;
    }

    public static void main(String[] args) {
        launch();
    }

}