package JavaGUI.Chating;

import JavaGUI.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        var chatClient = new ChatClient(null);
        while (true) {
            chatClient.serverWrite(
                    chatClient.serverWaitRead()
            );
        }
    }

    private Socket client = null;
    private BufferedReader serverReader;
    private PrintWriter serverWriter;
    private BufferedReader sysReader;
    private UserModel user;

    public ChatClient(UserModel user) {
        try {
            client = new Socket("localhost", 8888);
            serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            serverWriter = new PrintWriter(client.getOutputStream(), true);
            sysReader = new BufferedReader(new InputStreamReader(System.in));
            this.user = user;
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    // todo check server dead
    public void serverWrite(String msg) {
        if (!client.isClosed() && client.isConnected())
            serverWriter.println(msg);
    }

    public String serverWaitRead() {
        try {
            return serverReader.readLine();
//            while (!serverReader.ready()) ;
//            System.out.println(serverReader.readLine());
        } catch (IOException e) {
            if (e.getMessage().equals("Connection reset")) // todo fk this
                System.out.println(e.getMessage());
            else
                e.printStackTrace();
            return null;
        }
    }

    public String serverSafeWaitRead() { //todo make this safe?
        boolean connectionDead = false;
        try {
            while (!serverReader.ready()) {
                if (client.isOutputShutdown() || client.isInputShutdown() || client.isClosed()) {
                    connectionDead = true;
                    System.out.println("connectionDead = " + connectionDead);
                    break;
                }
            }
            if (!connectionDead)
                return serverReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void close() throws IOException {
        sysReader.close();
        serverWriter.close();
        serverReader.close();
        client.close();
    }

}
