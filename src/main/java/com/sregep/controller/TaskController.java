
package com.sregep.controller;

import com.sregep.entity.Task;
import com.sregep.service.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute(
            "tasks",
            taskService.getAllTasks()
        );

        return "tasks/index";
    }

    @GetMapping("/create")
    public String create() {
        return "tasks/create";
    }

    @PostMapping
    public String store(@ModelAttribute Task task) {
        taskService.createTask(task);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute(
            "task",
            taskService.getTaskById(id)
        );

        return "tasks/edit";
    }

    // @PostMapping("/update/{id}")
    // public String update(
    //         @PathVariable Long id,
    //         @ModelAttribute Task task) {

    //     taskService.updateTask(id, task);

    //     return "redirect:/tasks";
    // }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }
}
