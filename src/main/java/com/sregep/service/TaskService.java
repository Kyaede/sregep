package com.sregep.service;
import com.sregep.entity.Task;
import com.sregep.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("gak nemu")
                );
    }

    public Task createTask(Task task) {
        if (task.getTitle() == null ||
            task.getTitle().isBlank()) {
            throw new IllegalArgumentException(
                "gak oleh kosong"
            );
        }
        //coba dlu
        // task.setStatus(Task.Status.PENDING);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
