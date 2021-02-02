package IONetwork;


import java.io.Serializable;

public class Dog implements Serializable {
    public String name;
    public int age;

    transient
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
