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
}