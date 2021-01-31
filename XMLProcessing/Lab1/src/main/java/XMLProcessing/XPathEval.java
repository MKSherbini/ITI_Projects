package XMLProcessing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public class XPathEval {

    public static void main(String[] args) {
        new XPathEval();
    }

    XPathEval() {
        File xmlFile = new File(getClass().getResource("/XMLProcessing/notes.xml").getPath());
        try {
            Document document = GenerateHTMLFromXML.createDocumentFromFile(xmlFile);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression pathExpression = xPath.compile("//note[@type='tip']");
            NodeList nodeList = (NodeList) pathExpression.evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println(node.getTextContent());
            }
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
