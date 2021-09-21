package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Controller {

    DirectoryChooser directoryChooser = new DirectoryChooser();

    @FXML
    private TextField pathTextField;


    @FXML
    void openScheduler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scheduler.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Scheduler");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


    @FXML
    void sortDir(MouseEvent mouseEvent) {
        File defaultDir = new File("C:\\Users\\adamw\\Desktop");
        directoryChooser.setInitialDirectory(defaultDir);
        File selectedDir= directoryChooser.showDialog(new Stage());
        System.out.println(selectedDir.getPath());
        pathTextField.setText(selectedDir.getAbsolutePath());
    }
}
