package Lab2;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class BrowserItemView extends HBox {
    Label nameLabel;
    FontIcon browseFileIcon;
    //    {
//        getChildren().addAll(browseFileIcon, nameLabel);
//    }

    public BrowserItemView(BrowserItemModel m) {
        nameLabel = new Label(m.m_name);
        browseFileIcon = new FontIcon(m.getIconCode());
        getChildren().addAll(browseFileIcon, nameLabel);
    }

    public void setFolderIconOpen() {
        browseFileIcon.setIconLiteral("mdi2f-folder-open");
    }

    public void setFolderIconClose() {
        browseFileIcon.setIconLiteral("mdi2f-folder");
    }

}
