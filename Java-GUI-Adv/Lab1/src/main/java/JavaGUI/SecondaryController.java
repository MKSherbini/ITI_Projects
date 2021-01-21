package JavaGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SecondaryController implements Initializable {

    public Label lb_DefaultName;
    public TextField tf_Input;
    public ImageView img_DefaulImage;
    public VBox pane_ChatArea;
    public User user;


    public void onInputConfirm(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("item.fxml"));
            Parent item = fxmlLoader.load();
            Item ctrl = fxmlLoader.getController();
            ctrl.lb_ChatMessage.setText(tf_Input.getText());
            ctrl.lb_Image.setImage(user.img);
            ctrl.lb_Name.setText(user.name);

            pane_ChatArea.getChildren().add(item);
//            pane_ChatArea.getChildren().add(new Text(tf_Input.getText()));
            tf_Input.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = ((User) resourceBundle.getObject("user"));
        lb_DefaultName.setText(user.name);
        img_DefaulImage.setImage(user.img);
    }
}