package JavaGUI.Chating;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler extends Thread {
    private static final List<ClientHandler> m_clients = new ArrayList<>();
    private final Socket m_socket;
    private final String m_name;

    private OutputStream outputStream;
    private PrintWriter printWriter;
    private InputStream inputStream;
    private Scanner scanner;

    public ClientHandler(String name, Socket incoming) {
        System.out.println("ClientHandler.ClientHandler");
        m_clients.add(this);
        m_socket = incoming;
        m_name = name;
    }

    public void sendToOtherClients(String msg) {
        System.out.println("Clients count: " + m_clients.size());
        m_clients.parallelStream().forEach(client -> {
            if (this != client) {
                System.out.println("Sending: " + msg + " to " + client.m_name);
                client.printWriter.println(msg);
            }
        });
    }

    @Override
    public void run() {
        super.run();
        System.out.println("ClientHandler.run");
        try {
            // initialize
            outputStream = m_socket.getOutputStream();
            printWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
            inputStream = m_socket.getInputStream();
            scanner = new Scanner(inputStream);


            boolean done = false;
            while (!done && scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                if (msg.equalsIgnoreCase("bye")) {
                    done = true;
                }
                System.out.println("Sending: " + msg + " from " + m_name);
                sendToOtherClients(msg);
//                sendToOtherClients(m_name + ": " + msg);
            }

            // done
            m_socket.close();
            m_clients.remove(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ClientHandler.run Done");
    }
}
