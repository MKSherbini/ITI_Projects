package menuBarUsingSAX;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import org.junit.runner.RunWith;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class CallBackHandlerTest {
    Document document;
    Menu menu;
    MenuBar menubar;
    MenuItem menuItem;

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    //    @RunWith(JfxRunner.class)
//    @TestInJfxThread
    @org.junit.Test
    public void getMenuBar() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Platform.startup(() -> {
            try {
                SAXParser parser = factory.newSAXParser();
                CallBackHandler callBackHandler = new CallBackHandler();
//            System.out.println(CallBackHandlerTest.class.getResource("/test.xml").getPath());
//            MenuBar menuBara = new MenuBar();
                parser.parse(new File(CallBackHandlerTest.class.getResource("/test.xml").getPath()), callBackHandler);
                MenuBar menuBar = callBackHandler.getMenuBar();
                assertNotNull(menuBar);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
