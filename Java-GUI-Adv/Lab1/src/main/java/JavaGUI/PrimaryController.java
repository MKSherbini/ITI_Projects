package JavaGUI;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        App.switchToSecondary(new UserModel(txt_UserName.getText(), img_Image.getImage()));
    }

    public void onClickChoosePic(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            img_Image.setImage(new Image(file.toURI().toString(), 100, 100, false, true));
        }
    }
}
