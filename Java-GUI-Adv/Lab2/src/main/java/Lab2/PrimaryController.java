package Lab2;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    final DirectoryChooser dirChooser = new DirectoryChooser();


    public TextField directoryText;
    public TreeView<BrowserItemModel> treeView;
    public ListView<BrowserItemModel> listView;
    public FontIcon browseFileIcon;


    public void onClickBrowse(MouseEvent actionEvent) {
        var dir = dirChooser.showDialog(null);

        if (dir == null) return;
        directoryText.setText(dir.getAbsolutePath());

        loadTree(dir);
    }

    void loadTree(File dir) {
        if (!dir.exists()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "File: " + dir + " DOES NOT EXIST MR.AMIN ");
            alert.showAndWait();
        }

        var tree = App.genBrowserTree(dir);
        treeView.setRoot(tree);
    }

    void showOnListView(File fileDir) {
        listView.getItems().clear();
        for (String s : fileDir.list()) {
            listView.getItems().add(new BrowserItemModel(fileDir, s));
        }
    }

    public void onClickEnterBrowse(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)
            loadTree(new File(directoryText.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listView.setCellFactory(param -> new ListCell<BrowserItemModel>() {
            @Override
            protected void updateItem(BrowserItemModel model, boolean empty) {
                super.updateItem(model, empty);
                if (model == null || empty) {
                    setGraphic(null);
                } else {
                    BrowserItemView itemView = new BrowserItemView(model);
                    setGraphic(itemView);
                }
            }
        });

        treeView.setCellFactory(param -> new TreeCell<>() {
            @Override
            protected void updateItem(BrowserItemModel model, boolean empty) {
                super.updateItem(model, empty);
                if (model == null || empty) {
                    setGraphic(null);
                } else {
                    BrowserItemView itemView = new BrowserItemView(model);
                    itemView.setOnMouseClicked(mouseEvent -> {
                                directoryText.setText(model.getFileDir().getAbsolutePath());
                                showOnListView(model.getFileDir());
                            }
                    );
                    setGraphic(itemView);
                }
            }
        });
    }
}
