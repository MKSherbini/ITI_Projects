package XMLProcessing.code.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    int no;
    String country;
    String region;

    @Override
    public String toString() {
        return "Address{" +
                "no=" + no +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public Address(int no, String country, String region) {
        this.no = no;
        this.country = country;
        this.region = region;
    }

    public Address() {
    }
}
