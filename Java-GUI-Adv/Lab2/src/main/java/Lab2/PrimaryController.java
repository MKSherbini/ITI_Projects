package Lab2;

import javafx.event.ActionEvent;
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
    String[] m_depthLevels = new String[]{
            "Dynamic",
            "1",
            "2",
            "3",
            "Unlimited Depth",
            "Custom Limit",
    };
    int m_selectedDepth;
//    Map<String, Integer> m_depthLevels = new HashMap<>();
//
//    {
//        m_depthLevels.put("Unlimited Depth", -1);
//        m_depthLevels.put("1", 1);
//        m_depthLevels.put("2", 2);
//        m_depthLevels.put("3", 3);
//        m_depthLevels.put("Custom Limit", -2);
//    }

    public TextField directoryText;
    public TreeView<BrowserItemModel> treeView;
    public ListView<BrowserItemModel> listView;
    public FontIcon browseFileIcon;
    public TextField depthLimitText;
    public ComboBox<String> depthLimitCombo;


    public void onClickBrowse(MouseEvent actionEvent) {
        var dir = dirChooser.showDialog(null);

        if (dir == null) return;
        directoryText.setText(dir.getAbsolutePath());

        loadTree(dir);
    }

    void loadTree(File dir) {
        listView.getItems().clear();

        if (!dir.exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File: " + dir + " DOES NOT EXIST MR.AMIN ");
            alert.showAndWait();
            return;
        }

        var tree = App.genBrowserTree(dir, getDepthLevel());
        treeView.setRoot(tree);
    }

    void showOnListView(File fileDir) {
        listView.getItems().clear();

        if (fileDir.list() == null)
            listView.getItems().add(new BrowserItemModel(fileDir));
        else
            for (String s : fileDir.list()) {
                listView.getItems().add(new BrowserItemModel(fileDir, s));
            }
    }

    public void onClickEnterBrowse(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)
            loadTree(new File(directoryText.getText()));
    }

    void setDepthLevel(String level) {
        depthLimitText.setVisible(false);
        try {
            m_selectedDepth = Integer.parseInt(level);
        } catch (NumberFormatException e) {
            if (level == "Unlimited Depth") {
                m_selectedDepth = -1;
            } else {
                depthLimitText.setVisible(true);
            } //todo add dynamic
        }

        System.out.println(level + " : " + m_selectedDepth);
    }

    int getDepthLevel() {
        if (depthLimitText.isVisible()) {
            try {
                m_selectedDepth = Integer.parseInt(depthLimitText.getText());
                if (m_selectedDepth < 0) {
                    showErrorTrust();
                    m_selectedDepth = 0;
                }
            } catch (NumberFormatException e) {
                showErrorTrust();
                m_selectedDepth = 0;
            }
        }
        return m_selectedDepth;
    }

    void showErrorTrust() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Why?, I trusted you :'(");
        alert.showAndWait();
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
                    itemView.setOnMouseClicked(mouseEvent -> {
                                directoryText.setText(model.getFileDir().getAbsolutePath());
                            }
                    );
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

//        depthLimitCombo.setCellFactory(param -> new ListCell<>() {
//            @Override
//            protected void updateItem(String model, boolean empty) {
//                super.updateItem(model, empty);
//                if (model == null || empty) {
//                    setGraphic(null);
//                } else {
////                    this.setText(model);
//                    var item = new Label(model);
//                    item.setOnMousePressed(mouseEvent -> {
//                        setDepthLevel(model);
//                    });
//                    setGraphic(item);
//                }
//            }
//        });
        depthLimitCombo.getItems().setAll(m_depthLevels);
        depthLimitCombo.getSelectionModel().select(0);


        setDepthLevel(depthLimitCombo.getSelectionModel().getSelectedItem());

//        var ftest = new File("D:\\");
//        var test = new BrowserItemModel(ftest);
//        System.out.println(ftest.getName());
//        System.out.println(ftest.getParent());
//        System.out.println(ftest.getParentFile());
//        try {
//            System.out.println(ftest.getCanonicalPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(test);
//
//        ftest = new File("D:\\_Temp");
//        test = new BrowserItemModel(ftest);
//        System.out.println(ftest.getName());
//        System.out.println(ftest.getParent());
//        System.out.println(ftest.getParentFile());
//        try {
//            System.out.println(ftest.getCanonicalPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(test);
    }


    public void onClickDepthCombo(ActionEvent actionEvent) {
        setDepthLevel(depthLimitCombo.getSelectionModel().getSelectedItem());

    }
}
