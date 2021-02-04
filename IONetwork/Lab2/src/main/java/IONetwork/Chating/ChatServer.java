package IONetwork.Chating;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            int i = 1;
            while (true) {
                Socket incoming = serverSocket.accept();
                new ClientHandler("User" + i++, incoming).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
