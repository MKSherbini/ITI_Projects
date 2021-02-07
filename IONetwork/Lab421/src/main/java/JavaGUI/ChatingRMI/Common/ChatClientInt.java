package JavaGUI.ChatingRMI.Common;

import JavaGUI.Model.MessageModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInt extends Remote {
    void receive(MessageModel msg) throws RemoteException;
}
