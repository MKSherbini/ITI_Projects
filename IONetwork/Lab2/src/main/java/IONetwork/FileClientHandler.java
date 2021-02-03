package IONetwork;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileClientHandler extends Thread {
    private final Socket m_socket;
    private String m_name;

    private OutputStream outputStream;
    private PrintWriter printWriter;
    private InputStream inputStream;
    private Scanner scanner;

    public FileClientHandler(Socket incoming) {
        System.out.println("ClientHandler.ClientHandler");
        m_socket = incoming;
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

            m_name = scanner.nextLine();

            FileWriter fileWriter = new FileWriter(m_name);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                if (msg.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    fileWriter.append(msg + "\n");
                }
                System.out.println("Recieve" + msg);
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
