package JavaGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Base64;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mStage;

    @Override
    public void start(Stage stage) throws IOException {
        mStage = stage;
        stage.setTitle("Chat App");
        stage.setResizable(false);
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setResizable(boolean b) {
        mStage.setResizable(b);
    }


    static void setRoot(Parent p) throws IOException { // 1 change
        ((Stage) scene.getWindow()).setScene(
                scene = new Scene(p)
        );
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void switchToPrimary() {

    }

    static void switchToSecondary(UserModel user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        fxmlLoader.setResources(new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if (key == "user")
                    return user; // possible?
                else return null;
            }

            @Override
            public Enumeration<String> getKeys() {
                return null;
            }
        });

        Parent item = fxmlLoader.load();

        setRoot(item);
        setResizable(true);
        mStage.setMinHeight(400);
        mStage.setMinWidth(400);
    }

    static void addMessage(Pane chatArea, MessageModel message) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("messageItem.fxml"));
        Parent item = fxmlLoader.load();
        Item ctrl = fxmlLoader.getController();
        ctrl.lb_ChatMessage.setText(message.m_content);
        ctrl.lb_Image.setImage(message.m_from.img);
        ctrl.lb_Name.setText(message.m_from.name);
        ctrl.lb_DateTime.setText(message.m_date.toString());
        chatArea.getChildren().add(item);
//        ctrl.lb_ChatMessage.maxHeightProperty().bind(ctrl.lb_ChatMessage.heightProperty().multiply(0.75));
    }

    public static MessageModel deserializeFromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return (MessageModel) o;
    }

    public static String serializeToString(MessageModel o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static void main(String[] args) {
        launch();
    }

}