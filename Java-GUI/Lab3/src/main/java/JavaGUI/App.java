package JavaGUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.Optional;


/**
 * JavaFX App
 */
public class App extends Application {
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    final FileChooser fileChooser = new FileChooser();

    File file;
    TextArea body;

    @Override
    public void start(Stage stage) {

        var pane = new BorderPane();
        var mainBar = new MenuBar();

        mainBar.getMenus().addAll(
                new CustomMenu("File")
                        .addItem("New", this::onFileNew, "Ctrl+N")
                        .addItem("Open", this::onFileOpen, "Ctrl+O")
                        .addItem("Save", this::onFileSave, "Ctrl+S")
                        .addSeperator()
                        .addItem("Exit", this::onFileExit, "Ctrl+E"),
                new CustomMenu("Edit")
                        .addItem("Undo", this::onEditUndo)
                        .addItem("Redo", this::onEditRedo)
                        .addSeperator()
                        .addItem("Cut", this::onEditCut)
                        .addItem("Copy", this::onEditCopy)
                        .addItem("Paste", this::onEditPaste)
                        .addItem("Delete", this::onEditDelete)
                        .addSeperator()
                        .addItem("Select All", this::onEditSelectAll),
                new CustomMenu("Help")
                        .addItem("About", this::onHelpAbout)
        );
        body = new TextArea();

        pane.setTop(mainBar);
        pane.setCenter(body);

        var scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private void onHelpAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "After a lot of hardwork we finally did it");
        alert.setHeaderText("Made by Sherbini");
        alert.setTitle("About");

        alert.setGraphic(new ImageView(new Image("sponge.png", 50, 50, false, true)));
        alert.showAndWait();
//        alert.showAndWait()
//                .filter(response -> response == ButtonType.OK)
//                .ifPresent(response -> formatSystem());
    }

    private void onEditSelectAll(ActionEvent actionEvent) {
        body.selectAll();
    }

    private void onEditDelete(ActionEvent actionEvent) {
        body.deleteText(body.getSelection());
    }

    private void onEditPaste(ActionEvent actionEvent) {
        body.insertText(body.getSelection().getEnd(), clipboard.getString());
    }

    private void onEditCopy(ActionEvent actionEvent) {
        content.put(DataFormat.PLAIN_TEXT, body.getSelectedText());
        clipboard.setContent(content);
    }

    private void onEditCut(ActionEvent actionEvent) {
        onEditCopy(actionEvent);
        onEditDelete(actionEvent);
    }

    private void onEditRedo(ActionEvent actionEvent) {
        body.redo();
    }

    private void onEditUndo(ActionEvent actionEvent) {
        body.undo();
    }

    void onFileNew(ActionEvent actionEvent) {
        onFileSave(actionEvent);

        body.clear();
        file = null;
    }

    void onFileOpen(ActionEvent actionEvent) {
        onFileSave(actionEvent);

        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                FileReader fr = new FileReader(file);
                char[] cbuf = new char[1000];
                fr.read(cbuf);
                body.setText(new String(cbuf));
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    void onFileSave(ActionEvent actionEvent) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Save?");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saving");
            alert.showAndWait();
        } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ignoring save");
            alert.showAndWait();
            return;
        }

        if (file == null) {
            file = fileChooser.showOpenDialog(null);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "File: " + file);
        alert.showAndWait();

        try {
            FileWriter fr = new FileWriter(file);
            fr.write(body.getText());
            fr.close();
            alert = new Alert(Alert.AlertType.INFORMATION, "Saved");
            alert.showAndWait();

        } catch (IOException e) {
            alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    void onFileExit(ActionEvent actionEvent) {
        onFileSave(actionEvent);

        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }

}