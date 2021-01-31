package XMLProcessing.schema;

import XMLProcessing.schema.models.AddressType;
import XMLProcessing.schema.models.ObjectFactory;
import XMLProcessing.schema.models.PersonType;
import jakarta.xml.bind.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        JAXBContext jabeontext = JAXBContext.newInstance("XMLProcessing.schema.models");
        Marshaller marshaller = jabeontext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Unmarshaller unnarshaller = jabeontext.createUnmarshaller();

        marshallPerson(marshaller, null);

        var person = unmarshallPerson(unnarshaller);
        if (person != null) {
            person.getValue().setName("Sherbini");
            marshallPerson(marshaller, person);

            unmarshallPerson(unnarshaller);
        }
    }

    private static void marshallPerson(Marshaller marshaller, JAXBElement<PersonType> person) throws JAXBException, IOException {
        if (person == null) {
            ObjectFactory objectFactory = new ObjectFactory();
            AddressType addressType = objectFactory.createAddressType();
            addressType.setNumber(9);
            addressType.setStreet("street");
            PersonType personType = objectFactory.createPersonType();
            personType.setName("amin");
            personType.getAddress().add(addressType);
            JAXBElement<PersonType> newPerson = objectFactory.createPerson(personType);
            marshaller.marshal(newPerson, new FileWriter("person.xml"));
            return;
        }
        marshaller.marshal(person, new FileWriter("person.xml"));
    }

    private static JAXBElement<PersonType> unmarshallPerson(Unmarshaller unnarshaller) throws JAXBException, FileNotFoundException {
        Object object = unnarshaller.unmarshal(new FileReader("person.xml"));
        if (object instanceof JAXBElement) {
            JAXBElement<PersonType> jaxbElement = (JAXBElement<PersonType>) object;
            PrintPersonJaxb(jaxbElement);
            return jaxbElement;
        }
        return null;
    }

    private static void PrintPersonJaxb(JAXBElement<PersonType> jaxbElement) {
        System.out.println("Contents: ");

        PersonType jaxbElementValue = jaxbElement.getValue();
        System.out.println(jaxbElementValue.getName());
        List<AddressType> addressTypeList = jaxbElementValue.getAddress();
        addressTypeList.forEach(addressType1 -> {
            System.out.println(addressType1.getNumber());
            System.out.println(addressType1.getStreet());
        });
    }
}



