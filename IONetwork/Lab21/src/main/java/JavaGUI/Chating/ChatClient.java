package JavaGUI.Chating;

import JavaGUI.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

    Socket client = null;
    BufferedReader serverReader;
    PrintWriter serverWriter;
    BufferedReader sysReader;
    UserModel user;

    public ChatClient(UserModel user) {
        try {
            client = new Socket("localhost", 8888);
            serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            serverWriter = new PrintWriter(client.getOutputStream(), true);
            sysReader = new BufferedReader(new InputStreamReader(System.in));
            this.user = user;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverWrite(String msg) {
        serverWriter.println(msg);
    }

    public String serverWaitRead() {
        try {
            return serverReader.readLine();
//            while (!serverReader.ready()) ;
//            System.out.println(serverReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
