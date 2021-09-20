package sample;


import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

import javafx.stage.Stage;

import java.io.File;


public class Controller {

    DirectoryChooser directoryChooser = new DirectoryChooser();

    @FXML
    private TextField pathTextField;

    @FXML
    void sortDir(MouseEvent mouseEvent) {
        File defaultDir = new File("C:\\Users\\adamw\\Desktop");
        directoryChooser.setInitialDirectory(defaultDir);
        File selectedDir= directoryChooser.showDialog(new Stage());
        System.out.println(selectedDir.getPath());
        pathTextField.setText(selectedDir.getAbsolutePath());
    }
}
