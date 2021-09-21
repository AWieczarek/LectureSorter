package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Scheduler implements Initializable {

    @FXML
    private TextField lName;

    @FXML
    private TextField sTime;

    @FXML
    private TextField eTime;

    @FXML
    private TableView<Lesson> tableView;

    @FXML
    private TableColumn<Lesson, String> lNameCell;

    @FXML
    private TableColumn<Lesson, Date> sTimeCell;

    @FXML
    private TableColumn<Lesson, Date> eTimeCell;

    @FXML
    private TableColumn<Lesson, String> deleteCell;


    @FXML
    void addLesson(MouseEvent event) throws ParseException {
        String lessonName = lName.getText();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time startTime = new Time(formatter.parse(sTime.getText()).getTime());
        Time endTime = new Time(formatter.parse(eTime.getText()).getTime());

        System.out.println(lessonName + "  " + startTime + "  " + endTime);

        Lesson lesson = new Lesson(lessonName, "Temp", startTime, endTime);
        tableView.getItems().add(lesson);
    }

    ObservableList<Lesson> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lNameCell.setCellValueFactory(new PropertyValueFactory<Lesson, String>("lessonName"));
        deleteCell.setCellValueFactory(new PropertyValueFactory<Lesson, String>("delete"));
        sTimeCell.setCellValueFactory(new PropertyValueFactory<Lesson, Date>("startTime"));
        eTimeCell.setCellValueFactory(new PropertyValueFactory<Lesson, Date>("endTime"));

        tableView.getItems().addAll(list);
    }
}
