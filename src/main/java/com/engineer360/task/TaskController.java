package com.engineer360.task;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(
            @PathVariable Long projectId,
            @RequestBody TaskRequest request,
            Authentication authentication
    ) {

        return taskService.createTask(
                projectId,
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                authentication.getName()
        );
    }

    @GetMapping
    public List<Task> getTasks(
            @PathVariable Long projectId,
            Authentication authentication
    ) {

        return taskService.getTasks(
                projectId,
                authentication.getName()
        );
    }

    @GetMapping("/{taskId}")
    public Task getTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Authentication authentication
    ) {

        return taskService.getTask(
                projectId,
                taskId,
                authentication.getName()
        );
    }

    @PutMapping("/{taskId}")
    public Task updateTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody TaskRequest request,
            Authentication authentication
    ) {

        return taskService.updateTask(
                projectId,
                taskId,
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                authentication.getName()
        );
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Authentication authentication
    ) {

        taskService.deleteTask(
                projectId,
                taskId,
                authentication.getName()
        );
    }
}