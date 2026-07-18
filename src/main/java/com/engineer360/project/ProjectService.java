package com.engineer360.project;

import com.engineer360.user.User;
import com.engineer360.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            UserRepository userRepository
    ) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project createProject(
            String title,
            String description,
            String status,
            String githubUrl,
            String email
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        Project project = new Project(
                title,
                description,
                status,
                githubUrl,
                user
        );

        return projectRepository.save(project);
    }

    public List<Project> getProjects(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        return projectRepository.findByUser(user);
    }

    public Project getProject(Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        return projectRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new IllegalArgumentException("Project not found")
                );
    }

    public Project updateProject(
            Long id,
            String title,
            String description,
            String status,
            String githubUrl,
            String email
    ) {

        Project project = getProject(id, email);

        project.setTitle(title);
        project.setDescription(description);
        project.setStatus(status);
        project.setGithubUrl(githubUrl);

        return projectRepository.save(project);
    }

    public void deleteProject(Long id, String email) {

        Project project = getProject(id, email);

        projectRepository.delete(project);
    }
}