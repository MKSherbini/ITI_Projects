package IONetwork.FileBytes;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FileClientBytes {
    public static void main(String[] args) {
        try (Socket client = new Socket("localhost", 8899);
             BufferedInputStream serverReader = new BufferedInputStream(client.getInputStream());
             BufferedOutputStream serverWriter = new BufferedOutputStream(client.getOutputStream());
        ) {
            String fileName = "sponge.png";
            int packetSize = 15;
            fileName = fileName + " ".repeat(packetSize - fileName.length() - 1);

            int readSize = packetSize;
            byte[] buffer = new byte[packetSize];
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(FileClientBytes.class.getResource("/" + fileName).getPath()));
            buffer = fileName.getBytes(StandardCharsets.UTF_8);
            serverWriter.write(buffer);
            while ((readSize = fileReader.read(buffer)) > 0) {
                serverWriter.write(buffer);
                Thread.sleep(2);
            }
            serverWriter.write("bye".getBytes(StandardCharsets.UTF_8));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
