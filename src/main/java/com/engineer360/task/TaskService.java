package com.engineer360.task;

import com.engineer360.project.Project;
import com.engineer360.project.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    public TaskService(
            TaskRepository taskRepository,
            ProjectService projectService
    ) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }

    public Task createTask(
            Long projectId,
            String title,
            String description,
            String status,
            String email
    ) {

        Project project = projectService.getProject(projectId, email);

        Task task = new Task(
                title,
                description,
                status,
                project
        );

        return taskRepository.save(task);
    }

    public List<Task> getTasks(
            Long projectId,
            String email
    ) {

        Project project = projectService.getProject(projectId, email);

        return taskRepository.findByProject(project);
    }

    public Task getTask(
            Long projectId,
            Long taskId,
            String email
    ) {

        Project project = projectService.getProject(projectId, email);

        return taskRepository.findByIdAndProject(taskId, project)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found")
                );
    }

    public Task updateTask(
            Long projectId,
            Long taskId,
            String title,
            String description,
            String status,
            String email
    ) {

        Task task = getTask(projectId, taskId, email);

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);

        return taskRepository.save(task);
    }

    public void deleteTask(
            Long projectId,
            Long taskId,
            String email
    ) {

        Task task = getTask(projectId, taskId, email);

        taskRepository.delete(task);
    }
}