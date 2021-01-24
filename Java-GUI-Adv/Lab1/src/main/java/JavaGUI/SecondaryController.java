package JavaGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


    public void onInputConfirm(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER && tf_Input.getText().length() > 0) {
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
    }
}