package JavaGUI;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PrimaryController {
    final FileChooser fileChooser = new FileChooser();

    public TextField txt_UserName;
    @FXML
    private ImageView img_Image;

    public void onClickYalla(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        fxmlLoader.setResources(new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if (key == "user")
                    return new User(txt_UserName.getText(), img_Image.getImage());
//                    return new User(txt_UserName.getText(), new Image("sponge.png", 40, 40, false, true));
                else return null;
            }

            @Override
            public Enumeration<String> getKeys() {
                return null;
            }
        });
        Parent item = fxmlLoader.load();

        App.setRoot(item);
    }

    public void onClickChoosePic(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            img_Image.setImage(new Image(file.toURI().toString(), 100, 100, false, true));
        }
    }
}
