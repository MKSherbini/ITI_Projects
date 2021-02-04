package IONetwork.FileBytes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServerBytes {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8899)) {
            while (true) {
                Socket incoming = serverSocket.accept();
                new FileClientHandlerBytes(incoming).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
