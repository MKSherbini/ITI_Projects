package Lab2;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class BrowserItemView extends HBox {

    //    {
//        getChildren().addAll(browseFileIcon, nameLabel);
//    }

    public BrowserItemView(BrowserItemModel m) {
        Label nameLabel = new Label(m.m_name);
        FontIcon browseFileIcon = new FontIcon(m.getIconCode());
        getChildren().addAll(browseFileIcon, nameLabel);
    }
}
