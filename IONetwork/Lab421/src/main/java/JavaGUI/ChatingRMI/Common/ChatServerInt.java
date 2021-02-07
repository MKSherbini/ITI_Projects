package JavaGUI.ChatingRMI.Common;

import JavaGUI.Model.MessageModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInt extends Remote {
    void registerChatClient(ChatClientInt clientInt) throws RemoteException;

    void unregisterChatClient(ChatClientInt clientInt) throws RemoteException;

    void broadcastMessage(ChatClientInt clientInt, MessageModel msg) throws RemoteException;
}
