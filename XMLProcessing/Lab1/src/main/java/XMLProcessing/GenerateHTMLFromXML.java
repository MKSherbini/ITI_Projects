package XMLProcessing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.Writer;


/**
 * JavaFX App
 */
public class GenerateHTMLFromXML {


    public static void main(String[] args) {
        new GenerateHTMLFromXML();
    }

    public GenerateHTMLFromXML() {
        File xmlFile = new File(getClass().getResource("/XMLProcessing/notes.xml").getPath());
        File htmlFile = new File("notes.html");
        File xsltFile = new File(getClass().getResource("/XMLProcessing/notes.xslt").getPath());
        try {
            GenerateHTMLFromXMLBasedOnXslt(htmlFile, xmlFile, xsltFile);
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void GenerateHTMLFromXMLBasedOnXslt(File htmlFile, File xmlFile, File xsltFile) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Document xmlDocument = createDocumentFromFile(xmlFile);
        DOMSource xmlDomSource = new DOMSource(xmlDocument);

        StreamResult streamResult = new StreamResult(htmlFile);

        Document xsltDocument = createNSAwareDocumentFromFile(xsltFile);
        DOMSource xsltDomSource = new DOMSource(xsltDocument);

        transform(xsltDomSource, xmlDomSource, streamResult);
    }

    private void transform(DOMSource xsltDomSource, DOMSource xmlDomSource, StreamResult streamResult) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(xsltDomSource);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlDomSource, streamResult);
    }

    public static Document createDocumentFromFile(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(xmlFile);
    }

    private Document createNSAwareDocumentFromFile(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(xmlFile);
    }

}