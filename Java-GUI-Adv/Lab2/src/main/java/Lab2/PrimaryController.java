package Lab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    DepthMode m_depthMode;

    enum DepthMode {
        DYNAMIC,
        UNLIMITED,
        CUSTOM,
        PREDEFINED,
    }

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

//        var tree = App.genBrowserTree(dir, getDepthLevel());
        var tree = createNodeClass(new BrowserItemModel(dir));
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

    void setDepthMode(String level) {
        try {
            Integer.parseInt(level);
            m_depthMode = DepthMode.PREDEFINED;
        } catch (NumberFormatException e) {
            if (level.equals("Unlimited Depth")) {
                m_depthMode = DepthMode.UNLIMITED;
            } else if (level.equals("Dynamic")) {
                m_depthMode = DepthMode.DYNAMIC;
            } else if (level.equals("Custom Limit")) {
                m_depthMode = DepthMode.CUSTOM;
            }
        }

        if (m_depthMode == DepthMode.CUSTOM) {
            depthLimitText.setVisible(true);
        } else {
            depthLimitText.setVisible(false);
        }

//        System.out.println(level + " : " + m_selectedDepth);
    }

    int getDepthLevel() {
        var m_selectedDepth = 0;

        switch (m_depthMode) {
            case CUSTOM:
                try {
                    m_selectedDepth = Integer.parseInt(depthLimitText.getText());
                    if (m_selectedDepth < 0) {
                        showErrorTrust();
                        m_selectedDepth = 0;
                    }
                } catch (NumberFormatException e) {
                    showErrorTrust();
                }
                break;
            case PREDEFINED:
                m_selectedDepth = Integer.parseInt(depthLimitCombo.getSelectionModel().getSelectedItem());
                break;
            case UNLIMITED:
                m_selectedDepth = -1;
                break;
            case DYNAMIC:
                m_selectedDepth = 2;
                break;
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


//        treeView.setCellFactory(param -> new TreeCell<>() {
//            @Override
//            protected void updateItem(BrowserItemModel model, boolean empty) {
//                super.updateItem(model, empty);
//                if (model == null || empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(getGraphic());
//                    setText("");
////                    BrowserItemView itemView = new BrowserItemView(model);
////                    itemView.setOnMouseClicked(mouseEvent -> {
////                                directoryText.setText(model.getFileDir().getAbsolutePath());
////                                showOnListView(model.getFileDir());
////                            }
////                    );
////                    setGraphic(itemView);
//                }
//            }
//        });

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


        setDepthMode(depthLimitCombo.getSelectionModel().getSelectedItem());

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
        setDepthMode(depthLimitCombo.getSelectionModel().getSelectedItem());
    }


    FileTreeItem createNodeClass(BrowserItemModel f) {
        return new FileTreeItem(f, (model) -> {
            directoryText.setText(model.getFileDir().getAbsolutePath());
            showOnListView(model.getFileDir());
        });
    }

    // This method creates a TreeItem to represent the given File. It does this
    // by overriding the TreeItem.getChildren() and TreeItem.isLeaf() methods
    // anonymously, but this could be better abstracted by creating a
    // 'FileTreeItem' subclass of TreeItem. However, this is left as an exercise
    // for the reader.
    private TreeItem<BrowserItemModel> createNode(final BrowserItemModel f) {
        return new TreeItem<BrowserItemModel>(f) {
            // We cache whether the File is a leaf or not. A File is a leaf if
            // it is not a directory and does not have any files contained within
            // it. We cache this as isLeaf() is called often, and doing the
            // actual check on File is expensive.
            private boolean isLeaf;

            // We do the children and leaf testing only once, and then set these
            // booleans to false so that we do not check again during this
            // run. A more complete implementation may need to handle more
            // dynamic file system situations (such as where a folder has files
            // added after the TreeView is shown). Again, this is left as an
            // exercise for the reader.
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<BrowserItemModel>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    // First getChildren() call, so we actually go off and
                    // determine the children of the File contained in this TreeItem.
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    BrowserItemModel f = (BrowserItemModel) getValue();
                    isLeaf = f.isFile();
                }

                return isLeaf;
            }

            private ObservableList<TreeItem<BrowserItemModel>> buildChildren(TreeItem<BrowserItemModel> TreeItem) {
                BrowserItemModel f = TreeItem.getValue();
                if (f != null && f.m_file.isDirectory()) {
                    File[] files = f.m_file.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<BrowserItemModel>> children = FXCollections.observableArrayList();

                        for (File childFile : files) {
                            children.add(createNode(new BrowserItemModel(childFile)));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }
        };
    }
}
