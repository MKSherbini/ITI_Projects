package XMLProcessing.code.models;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@XmlRootElement(name = "student-class")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Student {

    int id;
    String name;
    String email;
    int age;
    List<Address> addresses = new ArrayList<>();

    public Student() {
    }

    public Student(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlElementWrapper(name = "addresses")
    @XmlElement(name = "address")
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", addresses=" + addresses +
                '}';
    }
}
