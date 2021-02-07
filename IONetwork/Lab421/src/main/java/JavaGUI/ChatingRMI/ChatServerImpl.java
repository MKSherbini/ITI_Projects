package JavaGUI.ChatingRMI;

import JavaGUI.MessageModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInt {
    ConcurrentLinkedQueue<ChatClientInt> clients;
//    List<ChatClientInt> clients;
    static int i;

    protected ChatServerImpl() throws RemoteException {
        clients = new ConcurrentLinkedQueue<>();
//        clients = new ArrayList<>();
        i++;
    }

    @Override
    public void registerChatClient(ChatClientInt clientInt) throws RemoteException {
        clients.add(clientInt);
    }

    @Override
    public void unregisterChatClient(ChatClientInt clientInt) throws RemoteException {
        clients.remove(clientInt);
    }

    @Override
    public void broadcastMessage(ChatClientInt clientInt, MessageModel msg) throws RemoteException {
        System.out.println("Trying to send to " + clients.size());
        clients.forEach(client -> {
            if (!client.equals(clientInt)) {
                System.out.println(clientInt + " " + i + " sending to " + client);
                try {
                    client.receive(msg);
                } catch (RemoteException e) {
                    System.out.println("Dropped client: " + clientInt);
                    clients.remove(client);
//                    e.printStackTrace();
                }
            }
        });
    }
}
