package XMLProcessing;

import javafx.scene.control.MenuBar;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Dom {
    public static void main(String[] args) {
        Document doc = null;
        try {
            File xmlFile = new File(Dom.class.getResource("/XMLProcessing/test.xml").getPath());
            var docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setIgnoringElementContentWhitespace(true);
            doc = docFactory.newDocumentBuilder().parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
        }
        Element root = doc.getDocumentElement();

        var children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            var menu = (Element) children.item(i);
            var menuNode = new CustomMenu(menu.getAttribute("name"));
            recurse(menu);
        }
    }

    private static void recurse(Element nodeXML) {
        var children = nodeXML.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            var node = (Element) children.item(i);

            System.out.println(node.getTagName() + ": " + node.getTextContent());
        }
    }
}
