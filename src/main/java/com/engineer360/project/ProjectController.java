package com.engineer360.project;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public Project createProject(
            @RequestBody ProjectRequest request,
            Authentication authentication
    ) {

        String email = authentication.getName();

        return projectService.createProject(
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                request.getGithubUrl(),
                email
        );
    }

    @GetMapping
    public List<Project> getProjects(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return projectService.getProjects(email);
    }

    @GetMapping("/{id}")
    public Project getProject(
            @PathVariable Long id,
            Authentication authentication
    ) {

        String email = authentication.getName();

        return projectService.getProject(id, email);
    }

    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequest request,
            Authentication authentication
    ) {

        String email = authentication.getName();

        return projectService.updateProject(
                id,
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                request.getGithubUrl(),
                email
        );
    }

    @DeleteMapping("/{id}")
    public void deleteProject(
            @PathVariable Long id,
            Authentication authentication
    ) {

        String email = authentication.getName();

        projectService.deleteProject(id, email);
    }
}