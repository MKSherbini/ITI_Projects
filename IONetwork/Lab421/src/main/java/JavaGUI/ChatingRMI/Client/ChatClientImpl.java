package JavaGUI.ChatingRMI.Client;

import JavaGUI.App;
import JavaGUI.ChatingRMI.Common.ChatClientInt;
import JavaGUI.Model.MessageModel;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClientInt {
    public Pane pane_ChatArea;


    public ChatClientImpl() throws RemoteException {
    }

    @Override
    public void receive(MessageModel msg) throws RemoteException {
        Platform.runLater(() -> {
            try {
                App.addMessage(pane_ChatArea, msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
