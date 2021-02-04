package IONetwork.Demos;
//Socket Server

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {
    public static void main(String args[]) throws IOException {
//A new service registered with the 1286 port
        ServerSocket ss = new ServerSocket(1286);
//Accept the connection request made with the server socket
        Socket s = ss.accept();
        //Establish the output stream from the socket connection
        OutputStream socketOutStream = s.getOutputStream();
        DataOutputStream socketDOS = new
                DataOutputStream(socketOutStream);
//Communicate with the socket data stream with a message
        socketDOS.writeUTF("Hello world!");
//Cleanup
        socketDOS.close();
        socketOutStream.close();
        s.close();
        ss.close();
    }
}