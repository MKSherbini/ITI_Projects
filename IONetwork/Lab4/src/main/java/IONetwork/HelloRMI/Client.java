package IONetwork.HelloRMI;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            Registry reg =
                    LocateRegistry.getRegistry(1111);
            Arrays.stream(reg.list()).forEach(System.out::println); // listing
            HelloInt helloRef = (HelloInt)
                    reg.lookup("HelloService");
            String str = helloRef.sayHello("JETS");
            System.out.println(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}