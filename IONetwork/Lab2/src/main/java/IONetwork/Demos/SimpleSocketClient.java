package IONetwork.Demos;


//Socket Client

import java.io.*;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String args[]) throws IOException {
//Establish a server connection through 1286 port
        Socket s = new Socket("localhost", 8189);
//Access the input stream of the server socket
        InputStream sIn = s.getInputStream();
//Wrap the socket input stream with data input stream
        DataInputStream socketDIS = new DataInputStream(sIn);

        OutputStream sOut = s.getOutputStream();
//Wrap the socket input stream with data input stream
        DataOutputStream socketOut = new DataOutputStream(sOut);
        socketOut.writeUTF("hello ekko");


//Read from the socket data input stream
        String testString = new String(socketDIS.readUTF());
//Print the data read
        System.out.println(testString);
//clean up
        socketDIS.close();
        sIn.close();
        s.close();
    }
}