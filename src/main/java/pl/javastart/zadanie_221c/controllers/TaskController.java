package pl.javastart.zadanie_221c.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.zadanie_221c.database.TaskPersistent;
import pl.javastart.zadanie_221c.database.TaskRepositoryInterface;
import pl.javastart.zadanie_221c.model.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskPersistent taskPersistent;

    @Autowired
    TaskRepositoryInterface taskInterface;

    @GetMapping("/showall")
    public String showAll(@RequestParam(required = false) String sort, Model model) {
        long toDays = 0;
        List<Task> taskList = null;

        if (sort == null) sort = "desc";

        switch (sort) {
            case "desc": {
                sort = "asc";
                model.addAttribute("sort", "asc");
                break;
            }
            case "asc": {
                sort = "desc";
                model.addAttribute("sort", "desc");
                break;
            }
        }

        taskList = taskPersistent.sortByDate(sort);

        for (Task tasks : taskList) {
            LocalDateTime startDateTime = LocalDateTime.of(tasks.getStartDate(), tasks.getStartTime());
            Temporal temporal = startDateTime;
            Temporal temporal1 = LocalDateTime.now();
            Duration duration = Duration.between(temporal, temporal1);
            toDays = duration.toDays();

            tasks.setEstEndTime(duration);
        }

        model.addAttribute("taskList", taskList);
        model.addAttribute("toDays", toDays);
        return "showall";
    }

    @GetMapping("/addtaskform")
    public String addTask(Model model) {
        model.addAttribute("task", new Task());
        return "addtask";
    }

    @PostMapping("/addtask")
    public String persistTask(Task task) {
        taskPersistent.save(task);
        return "redirect:showall";
    }

    @PostMapping("/changestatus")
    public String statusChange(@RequestParam long id) {
        taskPersistent.updateStatus(id);
        return "redirect:showall";
    }

    @PostMapping("/showallday")
    public String showallfromday(@RequestParam String date, Model model) {
        List<Task> taskList = taskPersistent.showDay(date);
        model.addAttribute("taskList", taskList);
        return "showall";
    }
}