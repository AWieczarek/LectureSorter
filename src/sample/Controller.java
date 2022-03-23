package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    String selectedDirectory = "C:\\Users\\adamw\\Desktop";

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
    void selectDir(MouseEvent mouseEvent) {
        File defaultDir = new File(selectedDirectory);
        directoryChooser.setInitialDirectory(defaultDir);
        File selectedDir= directoryChooser.showDialog(new Stage());
        System.out.println(selectedDir.getPath());
        pathTextField.setText(selectedDir.getAbsolutePath());
    }

    @FXML
    void sortDir(MouseEvent event) {
        String[] fileNames;
        String selectedPath = pathTextField.getText();
        File dir = new File(selectedPath);

        fileNames = dir.list();

        for (String files : fileNames) {
            if(Files.isRegularFile(Path.of(selectedPath + '\\' + files))){
//                moveToAppropriprateDir(files);
                checkDate(files);

            }
        }
    }

    private void checkDate(String date){
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd_HH-mm-ss");
        try {
            Date startTime = new Date(formatter.parse(date.replaceAll(".flv", "")).getTime());



        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void moveToAppropriprateDir(String fileName) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pathTextField.setText(selectedDirectory);
    }
}
