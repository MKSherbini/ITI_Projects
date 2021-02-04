package IONetwork.Demos;


//Socket Client

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String args[]) throws IOException {
//Establish a server connection through 1286 port
        Socket s = new Socket("localhost", 1286);
//Access the input stream of the server socket
        InputStream sIn = s.getInputStream();
//Wrap the socket input stream with data input stream
        DataInputStream socketDIS = new DataInputStream(sIn);
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