package IONetwork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8889)) {
            while (true) {
                Socket incoming = serverSocket.accept();
                new FileClientHandler(incoming).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
