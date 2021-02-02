package IONetwork;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    boolean dirty;

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
                        .addMenu(new CustomMenu("Help")
                                .addItem("About", this::onHelpAbout)
                                .addMenu(new CustomMenu("Help")
                                        .addItem("About", this::onHelpAbout)))
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
        dirty = true;
    }

    private void onEditPaste(ActionEvent actionEvent) {
        body.insertText(body.getSelection().getEnd(), clipboard.getString());
        dirty = true;
    }

    private void onEditCopy(ActionEvent actionEvent) {
        content.put(DataFormat.PLAIN_TEXT, body.getSelectedText());
        clipboard.setContent(content);
    }

    private void onEditCut(ActionEvent actionEvent) {
        onEditCopy(actionEvent);
        onEditDelete(actionEvent);
        dirty = true;
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
        dirty = true;
    }

    void onFileOpen(ActionEvent actionEvent) {
        onFileSave(actionEvent);

        file = fileChooser.showOpenDialog(null);
        if (file != null) {
//            readFileChars(file);
            readFileBytes(file);
        }
        dirty = true;
    }

    void readFileBytes(File file) {
        try (InputStream outputStream = new FileInputStream(file)) {
            byte[] cbuf = new byte[(int) file.length()];
            outputStream.read(cbuf);
            body.setText(new String(cbuf, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFileChars(File file) {
        try (FileReader fr = new FileReader(file)) {
            char[] cbuf = new char[(int) file.length()];
            fr.read(cbuf);
            body.setText(new String(cbuf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFileBytes(File file) {
        Alert alert;
        try (OutputStream fr = new FileOutputStream(file)) {
            fr.write(body.getText().getBytes(StandardCharsets.UTF_8));
            alert = new Alert(Alert.AlertType.INFORMATION, "Saved");
            alert.showAndWait();
        } catch (IOException e) {
            alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    void writeFileChars(File file) {
        Alert alert;
        try (FileWriter fr = new FileWriter(file)) {
            fr.write(body.getText());
            alert = new Alert(Alert.AlertType.INFORMATION, "Saved");
            alert.showAndWait();
        } catch (IOException e) {
            alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    void onFileSave(ActionEvent actionEvent) {
        if (!dirty) return;
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
        if (file != null) {
//            writeFileChars(file);
            writeFileBytes(file);
            dirty = false;
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