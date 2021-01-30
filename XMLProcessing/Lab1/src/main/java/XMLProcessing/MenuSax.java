package XMLProcessing;

import javafx.scene.control.MenuBar;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MenuSax extends DefaultHandler {
    MenuBar mainBar = new MenuBar();
    CustomMenu customMenu;
    boolean inItem;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        var elementValue = new String(ch, start, length);
        if (inItem) {
            customMenu.addItem(elementValue);
        }
    }


    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case "menu":
                customMenu = new CustomMenu(attr.getValue("name"));
                mainBar.getMenus().add(customMenu);
                break;
            case "menuitem":
                inItem = true;
                break;
            case "seperator":
                customMenu.addSeperator();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "menu":
                break;
            case "menuitem":
                inItem = false;
                break;
            case "seperator":
                break;
        }
    }

    public MenuBar getMainBar() {
        return mainBar;
    }
}
