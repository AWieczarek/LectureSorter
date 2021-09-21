package sample;

import javax.xml.crypto.Data;
import java.util.Date;

public class Lesson {
    private String lessonName, delete;
    private Date startTime, endTime;

    public Lesson(String lessonName, String delete, Date startTime, Date endTime) {
        this.lessonName = lessonName;
        this.delete = delete;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
