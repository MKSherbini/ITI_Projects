package creational.singleton;

import java.io.*;

public class SingletonSerializable implements Serializable {
    //    private static final long serialVersionUID = 1L; // to avoid exceptions in de-serialization duo to class structure changes
    private volatile static SingletonSerializable instance = null;

    public static SingletonSerializable getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonSerializable();
                }
            }
        }
        return instance;
    }

    // might need more testing
    // runs after readObject
    protected Object readResolve() {
        if (instance == null) {
            synchronized (SingletonLazy.class) { // just in case
                if (instance == null) {
                    instance = this;
                }
            }
        } else {
            // copy what you need from serialization
            instance.setI(i);
        }
        return instance;
    }

    private int i = 10;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    // serialize test
    public static void main(String[] args) {
        try {
            // Serialize to a file
            var instanceOne = SingletonSerializable.getInstance();
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                    "filename.ser"));
            instanceOne.setI(20);
            out.writeObject(instanceOne);
            out.close();

            instanceOne.setI(30);
            // deserialize file
            ObjectInput in = new ObjectInputStream(new FileInputStream(
                    "filename.ser"));
            SingletonSerializable instanceTwo = (SingletonSerializable) in.readObject();
            in.close();
            System.out.println(instanceOne.getI());
            System.out.println(instanceTwo.getI());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}