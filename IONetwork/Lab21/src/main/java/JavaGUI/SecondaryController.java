package JavaGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import JavaGUI.Chating.ChatClient;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    //    volatile boolean readTF;
    ChatClient chatClient;

    public void onInputConfirm(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER && tf_Input.getText().length() > 0) {
//            readTF = true;
            chatClient.serverWrite(userModel.name + ": " + tf_Input.getText());
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

        chatClient = new ChatClient(userModel);

        new Thread(() -> {
            while (true) {
                var msg = chatClient.serverWaitRead();
                var splits = msg.split(": ");
                Platform.runLater(() -> {
                    try {
                        App.addMessage(pane_ChatArea, new MessageModel(new UserModel(splits[0]), splits[1]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            }
        }).start();
//        Runnable clientTask = () -> {
//            try (Socket client = new Socket("localhost", 8888);
//                 BufferedReader serverReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//                 PrintWriter serverWriter = new PrintWriter(client.getOutputStream(), true);
//            ) {
//                while (true) {
//                    if (serverReader.ready()) {
//                        userModel.name = "other";
//                        Platform.runLater(() -> {
//                            try {
//                                App.addMessage(pane_ChatArea, new MessageModel(userModel, serverReader.readLine()));
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        });
//
//                        System.out.println(serverReader.readLine());
//                    }
//
//                    if (readTF) {
//                        readTF = false;
//                        userModel.name = "Me";
//
//                        Platform.runLater(() -> {
//                            String msg = tf_Input.getText();
//
//                            tf_Input.clear();
//                            try {
//                                App.addMessage(pane_ChatArea, new MessageModel(userModel, msg));
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            serverWriter.println(userModel.name + ": " + msg);
//                        });
//
////                        System.out.println("Me: " + msg);
////                        if (msg.equalsIgnoreCase("bye")) {
////                            return;
////                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        };
//        new Thread(clientTask).start();

    }
}