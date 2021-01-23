package Lab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Consumer;

public class FileTreeItem extends TreeItem<BrowserItemModel> {
    BrowserItemView itemView;
    BrowserItemModel itemModel;

    public FileTreeItem(BrowserItemModel item, Consumer<BrowserItemModel> event) {
        super(item);

        itemModel = item;
        // set graphics
        itemView = new BrowserItemView(item); // what about the cellfactory?
        itemView.setOnMouseClicked((e) -> {
            event.accept(item);
        });
        setGraphic(itemView);

        this.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler() {

            @Override
            public void handle(Event e) {
                System.out.println("branchExpandedEvent");

                FileTreeItem source = (FileTreeItem) e.getSource();
                if (!source.isLeaf() && source.isExpanded() && itemView == source.itemView) { // for open close folder
                    itemView.setFolderIconOpen();
                    System.out.println("Open folder icon");
                }

                if (source.getChildren().isEmpty()) {
                    if (item.m_file.isDirectory()) {
                        File[] files = item.m_file.listFiles();
                        if (files != null) {
                            for (File childFile : files) {
                                source.getChildren().add(new FileTreeItem(new BrowserItemModel(childFile), event));
                            }
                        }
                    }
                } else {
                    //if you want to implement rescanning a directory for changes this would be the place to do it
                }
            }
        });
        this.addEventHandler(TreeItem.branchCollapsedEvent(), new EventHandler() {
            @Override
            public void handle(Event e) {
                FileTreeItem source = (FileTreeItem) e.getSource(); // close folder here
                if (!source.isLeaf() && !source.isExpanded() && itemView == source.itemView) { // why dafuq should I check this
                    itemView.setFolderIconClose();
                    System.out.println("Close folder icon");
                }
            }
        });

    }

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
//    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;

//    @Override
//    public ObservableList<TreeItem<BrowserItemModel>> getChildren() {
//        if (isFirstTimeChildren) {
//            isFirstTimeChildren = false;
//
//            // First getChildren() call, so we actually go off and
//            // determine the children of the File contained in this TreeItem.
//            super.getChildren().setAll(buildChildren(this));
//        }
//        return super.getChildren();
//    }

    @Override
    public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
//            BrowserItemModel f = (BrowserItemModel) getValue();
            isLeaf = itemModel.isFile();
//            System.out.println(itemModel);
        }

        return isLeaf;
    }


//
//    private ObservableList<TreeItem<BrowserItemModel>> buildChildren(TreeItem<BrowserItemModel> TreeItem) {
////        BrowserItemModel f = TreeItem.getValue();
////        if (f != null && f.m_file.isDirectory()) {
////            File[] files = f.m_file.listFiles();
////            if (files != null) {
////                ObservableList<TreeItem<BrowserItemModel>> children = FXCollections.observableArrayList();
////                for (File childFile : files) {
////                    children.add(new FileTreeItem(new BrowserItemModel(childFile)));
////                }
////
////                return children;
////            }
////        }
//
//        return FXCollections.emptyObservableList();
//    }
}
