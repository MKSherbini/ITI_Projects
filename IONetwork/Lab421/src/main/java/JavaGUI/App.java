package JavaGUI;

import JavaGUI.Controller.Item;
import JavaGUI.Model.MessageModel;
import JavaGUI.Model.UserModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
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
        mStage.setOnCloseRequest(event -> mStage.close());
        switchToPrimary();
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setResizable(boolean b) {
        mStage.setResizable(b);
    }


    static void setRoot(Parent p) throws IOException { // 1 change
        mStage.setScene(
                scene = new Scene(p)
        );
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void switchToPrimary() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/JavaGUI/View/primary.fxml"));
        setResizable(false);

        setRoot((Parent) fxmlLoader.load());
    }

    public static void switchToSecondary(UserModel user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/JavaGUI/View/secondary.fxml"));
        fxmlLoader.setResources(new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if (key.equals("user"))
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

    public static void addMessage(Pane chatArea, MessageModel message) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/JavaGUI/View/messageItem.fxml"));
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

    public static byte[] imageToByteArray(Image img) {

        int w = (int) img.getWidth();
        int h = (int) img.getHeight();

        byte[] bytes = new byte[w * h * 4];

        img.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), bytes, 0, w * 4);
        return bytes;
    }

    public static void displayErrorAlert(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred");
        alert.setContentText(e);
        alert.showAndWait();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }

}