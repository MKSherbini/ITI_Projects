package utilities;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Serializer {
    private static volatile Serializer instance = null;

    private Serializer() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
    }

    public static Serializer getInstance() {
        if (instance == null) {
            synchronized (Serializer.class) {
                if (instance == null) {
                    instance = new Serializer();
                }
            }
        }
        return instance;
    }

    public <T> void serializeXML(Class<T> type, String path, T object) {
        try {
            JAXBContext jabeontext = JAXBContext.newInstance(type);
            Marshaller marshaller = jabeontext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new FileWriter(path));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T deserializeXML(Class<T> type, String str) {
        return deserializeXML(type, new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)));
    }

    public <T> T deserializeXML(Class<T> type, byte[] str) {
        return deserializeXML(type, new ByteArrayInputStream(str));
    }

    public <T> T deserializeXML(Class<T> type, InputStream inputStream) {
        T fromXml = null;
        try {
            JAXBContext jabeontext = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = jabeontext.createUnmarshaller();
            fromXml = (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return fromXml;
    }
}
