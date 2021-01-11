package Lab3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllInOne {
    public static void main(String[] args) {
        BoundedBall app1 = new BoundedBall(null);
        DateLabel app2 = new DateLabel();
        TextBus app3 = new TextBus();

        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.submit(app1);
        exec.submit(app2);
        exec.submit(app3);
    }
}
