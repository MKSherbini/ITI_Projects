package IONetwork.FileCharsUDP;

import IONetwork.FileChars.FileClient;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileClientHandlerUDP extends Thread {
    private final DatagramSocket m_socket;
    private final DatagramPacket m_requestPacket;
    private final String fileName;


    public FileClientHandlerUDP(DatagramSocket incoming, DatagramPacket requestPacket, String fileName) {
        System.out.println("ClientHandler.ClientHandler");
        m_socket = incoming;
        m_requestPacket = requestPacket;
        this.fileName = fileName;
    }


    @Override
    public void run() {
        super.run();
        System.out.println("ClientHandler.run");

//        BufferedInputStream
//                BufferedOutputStream
        try {
//            String fileName = "test.txt";
//            String fileName = "sponge.png";
//            String fileName = "sponge.wav";
//            String fileName = "video.mp4";
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(FileClient.class.getResource("/" + fileName).getPath()));
            byte[] bytesBuff = new byte[1000];
            int sizeRead;

            DatagramPacket responsePacket = new DatagramPacket(fileName.getBytes(StandardCharsets.UTF_8),
                    fileName.length(),
                    m_requestPacket.getAddress(),
                    m_requestPacket.getPort());
            m_socket.send(responsePacket);
            Thread.sleep(10);

            while ((sizeRead = fileReader.read(bytesBuff)) != -1) {
//                String msg = new String(bytesBuff);
//                System.out.println("msg = " + msg);

                responsePacket = new DatagramPacket(bytesBuff,
                        sizeRead,
                        m_requestPacket.getAddress(),
                        m_requestPacket.getPort());
                m_socket.send(responsePacket);
                Thread.sleep(10);
            }

            responsePacket = new DatagramPacket("bye".getBytes(StandardCharsets.UTF_8),
                    3,
                    m_requestPacket.getAddress(),
                    m_requestPacket.getPort());
            m_socket.send(responsePacket);
            Thread.sleep(10);


            // done
//            m_socket.close();
            fileReader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ClientHandler.run Done");
    }
}
