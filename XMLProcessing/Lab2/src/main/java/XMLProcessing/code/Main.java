package XMLProcessing.code;

import XMLProcessing.code.models.Address;
import XMLProcessing.code.models.Student;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        JAXBContext jabeontext = JAXBContext.newInstance(Student.class);
        Student student = new Student(123, "amin", "amin@mcit.gov.eq", 25);
        student.getAddresses().add(new Address(33, "eg", "benha"));
        student.getAddresses().add(new Address(22, "ancient eg", "pyramids"));
        Marshaller marshaller = jabeontext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(student, new FileWriter("student.xml"));
        Unmarshaller unmarshaller = jabeontext.createUnmarshaller();
        Student studentFromXml = (Student) unmarshaller.unmarshal(new FileReader("student.xml"));
        System.out.println(studentFromXml);
    }
}
