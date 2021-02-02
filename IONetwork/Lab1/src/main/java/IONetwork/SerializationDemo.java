package IONetwork;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        new SerializationDemo();
    }

    public SerializationDemo() {
        serialize();
        deserialize();
    }

    void serialize() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serial"))) {
            Dog falco = new Dog("Falco", 4, true);
            System.out.println("falco = " + falco);
            objectOutputStream.writeObject(falco);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void deserialize() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serial"))) {
            Dog falco;
            falco = (Dog) objectInputStream.readObject();
            System.out.println("falco = " + falco);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
