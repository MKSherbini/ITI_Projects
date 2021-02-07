package JavaGUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ResourceBundle;

import JavaGUI.App;
import JavaGUI.ChatingRMI.Client.ChatClientImpl;
import JavaGUI.ChatingRMI.Common.ChatServerInt;
import JavaGUI.Model.MessageModel;
import JavaGUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class SecondaryController implements Initializable {

    public Label lb_DefaultName;
    public TextField tf_Input;
    public ImageView img_DefaultImage;
    public Pane pane_ChatArea;
    public ScrollPane pane_scrollPane;

    public UserModel userModel;
    public Button btn_connect;
    ChatServerInt chatServerInt;
    ChatClientImpl chatClient;

    public void onInputConfirm(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER && tf_Input.getText().length() > 0) {
            var msg = new MessageModel(userModel, tf_Input.getText());
            new Thread(() -> {
                try {
                    chatServerInt.broadcastMessage(chatClient, msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();

            App.addMessage(pane_ChatArea, new MessageModel(userModel, tf_Input.getText()));
            tf_Input.clear();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = ((UserModel) resourceBundle.getObject("user"));
        lb_DefaultName.setText(userModel.name);
        img_DefaultImage.setImage(userModel.img);
        pane_scrollPane.vvalueProperty().bind(pane_ChatArea.heightProperty());

        tf_Input.setDisable(true);
    }

    private void connectToServer() throws RemoteException, NotBoundException {
        chatClient = new ChatClientImpl();
        chatClient.pane_ChatArea = pane_ChatArea;
        chatServerInt = (ChatServerInt) LocateRegistry.getRegistry(1112).lookup("ChatServerInt");
        chatServerInt.registerChatClient(chatClient);
    }

    private void disconnectFromServer() throws RemoteException {
        if (chatServerInt != null) {
            chatServerInt.unregisterChatClient(chatClient);
            chatServerInt = null;
            chatClient = null;
        }
    }

    public void onConnectClick(ActionEvent actionEvent) {
        if (btn_connect.getText().equals("Connect")) {
            try {
                connectToServer();
                btn_connect.setText("Disconnect");
                tf_Input.setDisable(false);
            } catch (RemoteException | NotBoundException e) {
//            e.printStackTrace();
                App.displayErrorAlert("Connecting failed try again later");
            }
        } else {
            try {
                disconnectFromServer();
                btn_connect.setText("Connect");
                tf_Input.setDisable(true);
            } catch (RemoteException e) {
//            e.printStackTrace();
                App.displayErrorAlert("Server down");

            }
        }
    }
}