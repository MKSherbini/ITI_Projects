package IONetwork;

import java.io.*;
import java.util.Base64;

public class SerializationDemo {
    public static void main(String[] args) {
        new SerializationDemo();
    }

    public SerializationDemo() {
//        serialize();
//        deserialize();
        Dog falco = new Dog("Falco", 4, true);

        try {
            var s = serializeToString(falco);
            System.out.println("s = " + s);
            var d = deserializeFromString(s);
            System.out.println("d = " + d);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Dog deserializeFromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return (Dog) o;
    }

    private static String serializeToString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
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
