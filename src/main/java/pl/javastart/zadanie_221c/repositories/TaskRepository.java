package pl.javastart.zadanie_221c.repositories;

import org.springframework.stereotype.Repository;
import pl.javastart.zadanie_221c.model.Task;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> taskList;

    public TaskRepository() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getAll() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}