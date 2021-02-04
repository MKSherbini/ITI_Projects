package IONetwork.FileChars;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        try (Socket client = new Socket("localhost", 8889);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter serverWriter = new PrintWriter(client.getOutputStream(), true);
        ) {
            String fileName = "test.txt";
            BufferedReader fileReader = new BufferedReader(new FileReader(FileClient.class.getResource("/" + fileName).getPath()));
            serverWriter.println(fileName);
            while (fileReader.ready()) {
                serverWriter.println(fileReader.readLine());
                Thread.sleep(200);
            }
            serverWriter.println("bye");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
