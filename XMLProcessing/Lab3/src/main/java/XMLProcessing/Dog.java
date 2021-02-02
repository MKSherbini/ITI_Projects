package XMLProcessing;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbProperty;

//@JsonbNillable
public class Dog {
    public String name;
    public int age;

    @JsonbProperty("writing-name")
    public String getName() {
        return name;
    }

    @JsonbProperty("reading-name")
    public void setName(String name) {
        this.name = name;
    }

    public boolean bitable;

    public Dog() {
    }

    public Dog(String name, int age, boolean bitable) {
        this.name = name;
        this.age = age;
        this.bitable = bitable;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bitable=" + bitable +
                '}';
    }
}
