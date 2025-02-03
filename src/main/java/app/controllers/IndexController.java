package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import app.repositories.TaskRepository;

@Controller
public class IndexController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path={"/", "/tasks"})
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }
}