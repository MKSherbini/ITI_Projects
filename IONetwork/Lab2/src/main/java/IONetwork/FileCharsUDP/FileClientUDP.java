package IONetwork.FileCharsUDP;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileClientUDP {
    public static void main(String[] args) {
//        simple();
        bad();
    }

    private static void bad() {
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner s = new Scanner(System.in);
            byte[] bytes = s.nextLine().getBytes(StandardCharsets.UTF_8);
            InetAddress server = InetAddress.getByName("localhost");
            DatagramPacket request = new DatagramPacket(bytes, bytes.length, server, 3000);
            System.out.println("sending: " + bytes.length);
            socket.send(request);

            byte[] bytesBuff = new byte[1000];
            DatagramPacket response = new DatagramPacket(bytesBuff, bytesBuff.length);
            socket.receive(response);
            String name = new String(response.getData()).substring(0, response.getLength());
            System.out.println("name = " + name);
//            FileOutputStream fileWriter = new FileOutputStream(name);
            var fileWriter = new BufferedOutputStream(new FileOutputStream(name));
            int i = 0;
            while (true) {
                i++;
                if (i % 10 == 0) System.out.println("10 lines");
                socket.receive(response);
//                System.out.println("response.getData() = " + new String(response.getData()).substring(0, response.getLength()));
                if (new String(response.getData()).substring(0, response.getLength()).equalsIgnoreCase("bye")) {
                    break;
                }
                fileWriter.write(response.getData(), 0, response.getLength());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simple() {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] bytes = "hello".getBytes();
            InetAddress server = InetAddress.getByName("localhost");
            DatagramPacket request = new DatagramPacket(bytes, bytes.length, server, 3000);
            socket.send(request);

            byte[] bytesBuff = new byte[1000];
            DatagramPacket response = new DatagramPacket(bytesBuff, bytesBuff.length);
            socket.receive(response);
            System.out.println("response.getData() = " + new String(response.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
