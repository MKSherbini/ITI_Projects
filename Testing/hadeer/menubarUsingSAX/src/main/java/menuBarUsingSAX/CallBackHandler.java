package menuBarUsingSAX;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CallBackHandler extends DefaultHandler {
    MenuBar menuBar;
    Menu menu;
    MenuItem menuItem;

    CallBackHandler() {
    }

    @Override
    public void startDocument() {
        menuBar = new MenuBar();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("Menu")) {
            menu = new Menu(attributes.getValue("value"));
            menuBar.getMenus().add(menu);
        } else if (qName.equals("MenuItem")) {
            menuItem = new MenuItem();
            menu.getItems().add(menuItem);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("Menu")) {
            menu = null;
        } else if (qName.equals("MenuItem")) {
            menuItem = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = String.copyValueOf(ch, start, length);
        if (value.charAt(0) == '\n' && menuItem == null)
            return;
        menuItem.setText(value);

    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
