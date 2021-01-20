package JavaGUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

import java.util.ArrayList;

public class CustomMenu extends Menu { // or builder?
    CustomMenu(String name) {
        super("_" + name);
        setMnemonicParsing(true);
    }

    public CustomMenu addSeperator() {
        getItems().add(new SeparatorMenuItem());
        return this;
    }

    public CustomMenu addItem(String name) {
        addItem(name, null, null);
        return this;
    }

    public CustomMenu addItem(String name, EventHandler<ActionEvent> action) {
        addItem(name, action, null);
        return this;
    }

    public CustomMenu addItem(String name, EventHandler<ActionEvent> action, String keyComb) {
        var item = new MenuItem("_" + name);
        item.setMnemonicParsing(true);
        if (action != null)
            item.setOnAction(action);
        if (keyComb != null)
            item.setAccelerator(KeyCombination.keyCombination(keyComb));
        getItems().add(item);
        return this;
    }
}
