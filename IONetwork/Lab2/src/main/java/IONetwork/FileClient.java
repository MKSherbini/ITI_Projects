package IONetwork;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        try (Socket client = new Socket("localhost", 8889);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter serverWriter = new PrintWriter(client.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in);
//             BufferedReader sysReader = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String fileName = "test.txt";
//            String fileName = "sponge.png";
//                String fileName = scanner.nextLine();
//                if (fileName.equalsIgnoreCase("bye")) break;
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
