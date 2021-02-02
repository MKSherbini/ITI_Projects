package XMLProcessing;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Sax {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        DemoSax handler;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        handler = new DemoSax();
        saxParser.parse(new File(Sax.class.getResource("/XMLProcessing/test.xml").getPath()), handler);
    }
}
