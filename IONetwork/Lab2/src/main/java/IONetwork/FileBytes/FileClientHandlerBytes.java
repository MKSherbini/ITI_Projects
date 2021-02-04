package IONetwork.FileBytes;

import IONetwork.App;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileClientHandlerBytes extends Thread {
    private final Socket m_socket;
    private String m_name;


    public FileClientHandlerBytes(Socket incoming) {
        System.out.println("ClientHandler.ClientHandler");
        m_socket = incoming;
    }


    @Override
    public void run() {
        super.run();
        System.out.println("ClientHandler.run");
        try {
            // initialize
            BufferedInputStream inputStream = new BufferedInputStream(m_socket.getInputStream());
            BufferedOutputStream outputStream = new BufferedOutputStream(m_socket.getOutputStream());
            int packetSize = 15;
            int readSize = packetSize;
            byte[] buffer = new byte[packetSize];

            inputStream.read(buffer);
            m_name = new String(buffer, StandardCharsets.UTF_8);
            System.out.println("m_name = " + m_name);
            var fileWriter = new BufferedOutputStream(new FileOutputStream(m_name));
//            var fileWriter = new BufferedOutputStream(new FileOutputStream(FileClient.class.getResource(m_name).getFile()));
            while (inputStream.read(buffer) > 0) {
                String msg = new String(buffer, StandardCharsets.UTF_8);
                if (msg.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    fileWriter.write(buffer);
                }
                System.out.println("Received" + msg);
            }

            // done
            m_socket.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ClientHandler.run Done");
    }
}
