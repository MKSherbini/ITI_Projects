package XMLProcessing;

import javafx.scene.control.MenuBar;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DemoSax extends DefaultHandler {



    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        var elementValue = new String(ch, start, length);
        System.out.println("elementValue = " + elementValue);
    }


    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        System.out.println("startElement: " + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("endElement: " + qName);

    }

}
