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

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskPersistent taskPersistent;

    @Autowired
    TaskRepositoryInterface taskInterface;

    @GetMapping("/showall")
    public String showAll(Model model){
        List<Task> taskList = taskPersistent.getAll();
        model.addAttribute("taskList", taskList);
        return "showall";
    }
    @GetMapping("/addtaskform")
    public String addTask(Model model){
        model.addAttribute("task", new Task());
        return "addtask";
    }

    @PostMapping("/addtask")
    public String persistTask(Task task){
        taskPersistent.save(task);
        return "redirect:showall";
    }
    @PostMapping("/changestatus")
    public String statusChange(@RequestParam long id, Model model){
        taskPersistent.updateStatus(id);
        return "redirect:showall";
    }

}
