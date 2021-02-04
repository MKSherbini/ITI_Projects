package IONetwork.Chating;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket client = new Socket("localhost", 8888);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter serverWriter = new PrintWriter(client.getOutputStream(), true);
             BufferedReader sysReader = new BufferedReader(new InputStreamReader(System.in));
        ) {
            while (true) {
                if (serverReader.ready())
                    System.out.println(serverReader.readLine());

                if (sysReader.ready()) {
                    String msg = sysReader.readLine();
                    System.out.println("Me: " + msg);
                    if (msg.equalsIgnoreCase("bye")) {
                        return;
                    }
                    serverWriter.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
