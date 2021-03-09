package creational.singleton;

/**
 * Ensure a class only has one instance, and provide a global point of access to it.
 */

// only one constructor, if you need multiple then you probably want to use factory
// cons: over use makes the application more complicated
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
