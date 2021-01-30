package XMLProcessing;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var pane = new BorderPane();
        var mainBar = new MenuBar();

        mainBar.getMenus().addAll(
                new CustomMenu("File")
                        .addItem("New")
                        .addItem("Open")
                        .addItem("Save")
                        .addSeperator()
                        .addItem("Exit"),
                new CustomMenu("Edit")
                        .addItem("Undo")
                        .addItem("Redo")
                        .addSeperator()
                        .addItem("Cut")
                        .addItem("Copy")
                        .addItem("Paste")
                        .addItem("Delete")
                        .addSeperator()
                        .addItem("Select All"),
                new CustomMenu("Help")
                        .addItem("About")
        );

        var body = new TextArea();

//        System.out.println(getClass().getResource("/XMLProcessing/test.xml").getPath());
//        System.out.println(new File(getClass().getResource("/XMLProcessing/test.xml").getPath()));

//        Platform.exit();

//        pane.setTop(mainBar);
        pane.setTop(readDom());
//        pane.setTop(readSax());
        pane.setCenter(body);

        var scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();
//        Platform.exit();
    }

    MenuBar readSax() {
        MenuSax handler;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            handler = new MenuSax();
            saxParser.parse(new File(getClass().getResource("/XMLProcessing/test.xml").getPath()), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            System.out.println("fail");
            return new MenuBar();
        }
        System.out.println("success");
        return handler.getMainBar();
    }

    MenuBar readDom() {
        var menuBar = new MenuBar();
        Document doc = null;
        try {
            File xmlFile = new File(getClass().getResource("/XMLProcessing/test.xml").getPath());
            var docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setIgnoringElementContentWhitespace(true);
            doc = docFactory.newDocumentBuilder().parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return new MenuBar();
        }
        Element root = doc.getDocumentElement();
        var children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            var menu = (Element) children.item(i);

            var menuNode = new CustomMenu(menu.getAttribute("name"));
            var menuItems = menu.getChildNodes();
            for (int j = 0; j < menuItems.getLength(); j++) {
                if (menuItems.item(j).getNodeType() != Node.ELEMENT_NODE) continue;
                var menuItem = (Element) menuItems.item(j);
                if (menuItem.getNodeName() == "seperator") {
                    menuNode.addSeperator();
                } else {
                    menuNode.addItem(menuItem.getTextContent());
                }
            }
            menuBar.getMenus().add(menuNode);
        }
        return menuBar;
    }

    public static void main(String[] args) {
        launch();
    }

}