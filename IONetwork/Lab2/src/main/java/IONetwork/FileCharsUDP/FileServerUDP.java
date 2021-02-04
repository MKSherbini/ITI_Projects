package IONetwork.FileCharsUDP;

import java.io.IOException;
import java.net.*;

public class FileServerUDP {
    public static void main(String[] args) {
//        simple();
        bad();
    }

    private static void bad() {
        try (DatagramSocket datagramSocket = new DatagramSocket(3000);) {
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(requestPacket);
                new FileClientHandlerUDP(datagramSocket, requestPacket,
                        new String(requestPacket.getData()).substring(0, requestPacket.getLength())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simple() {
        try (DatagramSocket datagramSocket = new DatagramSocket(3000);) {
            byte[] buffer = new byte[1000];
            DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(requestPacket);
            DatagramPacket responsePacket = new DatagramPacket(requestPacket.getData(),
                    requestPacket.getLength(),
                    requestPacket.getAddress(),
                    requestPacket.getPort());
            datagramSocket.send(responsePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
