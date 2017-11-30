package pl.javastart.zadanie_221c.model;

import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "zadanie")
public class Task {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_name", length = 30, nullable = false)
    private String taskName;
    private int durationTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;
    @Column(name = "Status", length = 2, nullable = false)
    private boolean doneStatus;

    public Task(String taskName, int durationTime, LocalDate startTime, boolean doneStatus) {
        this.taskName = taskName;
        this.durationTime = durationTime;
        this.startTime = startTime;
        this.doneStatus = doneStatus;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public boolean isDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(boolean doneStatus) {
        this.doneStatus = doneStatus;
    }

}
