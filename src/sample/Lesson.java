package sample;

import com.google.gson.JsonElement;

import java.util.Date;

public class Lesson extends JsonElement {
    private String lessonName;
    private String day;
    private Date startTime;
    private Date endTime;

    public Lesson(String lessonName, String day, Date startTime, Date endTime) {
        this.lessonName = lessonName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getLessonName() {
        return this.lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public JsonElement deepCopy() {
        return null;
    }
}
