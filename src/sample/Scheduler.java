package sample;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Scheduler implements Initializable {
    private static Scheduler instance = new Scheduler();

    public static Scheduler getInstance(){
        return instance;
    }

    String[] posibleDays = new String[]{"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek"};
    List<String> posibleDaysList = Arrays.asList(posibleDays);

    @FXML
    private TextField lName;

    @FXML
    private TextField sTime;

    @FXML
    private TextField eTime;

    @FXML
    private TextField day;

    @FXML
    private TableView<Lesson> tableView;

    @FXML
    private TableColumn<Lesson, String> lNameCell;

    @FXML
    private TableColumn<Lesson, String > dayCell;

    @FXML
    private TableColumn<Lesson, Date> sTimeCell;

    @FXML
    private TableColumn<Lesson, Date> eTimeCell;


    @FXML
    void addLesson(MouseEvent event) throws ParseException {
        String lessonName = lName.getText();
        String lessonDay = day.getText();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time startTime = new Time(formatter.parse(sTime.getText()).getTime());
        Time endTime = new Time(formatter.parse(eTime.getText()).getTime());

        if(!posibleDaysList.contains(lessonDay)){
            System.out.println("Nie ma takiego dnia!");
            return;
        }
        if(endTime.getTime() - startTime.getTime() < 0){
            System.out.println("Zła godzina!");
            return;
        }
        System.out.println(lessonName + "  " + startTime + "  " + endTime);
        Lesson lesson = new Lesson(lessonName, lessonDay, startTime, endTime);
        tableView.getItems().add(lesson);

        lName.clear();
        day.clear();
        sTime.clear();
        eTime.clear();
    }

    @FXML
    void deleteLesson(MouseEvent event) {
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
    }

    public ObservableList<Lesson> getList() {
        return list;
    }

    ObservableList<Lesson> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lNameCell.setCellValueFactory(new PropertyValueFactory<Lesson, String>("lessonName"));
        dayCell.setCellValueFactory(new PropertyValueFactory<Lesson, String>("day"));
        sTimeCell.setCellValueFactory(new PropertyValueFactory<Lesson, Date>("startTime"));
        eTimeCell.setCellValueFactory(new PropertyValueFactory<Lesson, Date>("endTime"));

        readData();

        tableView.getItems().addAll(list);

    }

    private void readData() {
        try (FileReader file = new FileReader("Schedule.json")) {
            Gson gson = new Gson();

            JsonArray[] week = gson.fromJson(file, JsonArray[].class);

            for(JsonArray days : week){
                for(JsonElement lessonJson : days){
                    Lesson lesson = getLesson(lessonJson);
                    tableView.getItems().add(lesson);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Lesson getLesson(JsonElement lessonJson) {
        Lesson lesson = new Lesson(
                lessonJson.getAsJsonObject().get("lessonName").toString().replaceAll("\"", ""),
                lessonJson.getAsJsonObject().get("day").toString().replaceAll("\"", ""),
                stringToDate(lessonJson.getAsJsonObject().get("startTime").toString().replaceAll("\"", "")),
                stringToDate(lessonJson.getAsJsonObject().get("endTime").toString().replaceAll("\"", ""))
        );
        return lesson;
    }

    private Time stringToDate(String timeString) {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time time = null;
        try {
            time = new Time(formatter.parse(timeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    @FXML
    void saveSchedule(MouseEvent event) throws IOException {
        JsonArray week = new JsonArray();
        JsonArray monday = new JsonArray();
        JsonArray tuesday = new JsonArray();
        JsonArray wednesday = new JsonArray();
        JsonArray thursday = new JsonArray();
        JsonArray friday = new JsonArray();
        

        for (int i = 0; i < tableView.getItems().size(); i++){
            JsonObject temp = new JsonObject();
            ObservableList<Lesson> info = tableView.getItems();
            temp.addProperty("lessonName", info.get(i).getLessonName());
            temp.addProperty("day", info.get(i).getDay());
            temp.addProperty("startTime", info.get(i).getStartTime().toString());
            temp.addProperty("endTime", info.get(i).getEndTime().toString());

            if(info.get(i).getDay().equals("Poniedziałek")) monday.add(temp);
            else if(info.get(i).getDay().equals("Wtorek")) tuesday.add(temp);
            else if(info.get(i).getDay().equals("Środa")) wednesday.add(temp);
            else if(info.get(i).getDay().equals("Czwartek")) thursday.add(temp);
            else friday.add(temp);

        }
        
        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        week.add(thursday);
        week.add(friday);

        try (FileWriter file = new FileWriter("Schedule.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyWeek = gson.toJson(week);
            file.write(prettyWeek);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }
}
