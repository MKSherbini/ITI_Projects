package creational.singleton;

public class SingletonLazy {
    private static volatile SingletonLazy instance = null;

    private SingletonLazy() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }

    public static synchronized SingletonLazy getInstanceBad() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
