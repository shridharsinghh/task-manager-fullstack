package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.model.Project;
import com.taskmanager.taskmanager.model.Role;
import com.taskmanager.taskmanager.model.User;
import com.taskmanager.taskmanager.repository.ProjectRepository;
import com.taskmanager.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    // Create Project
    public Project createProject(String name, String userEmail) {

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can create project");
        }

        Project project = Project.builder()
                .name(name)
                .owner(user)
                .build();

        return projectRepo.save(project);
    }

    // Get all projects of user
    public List<Project> getUserProjects(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return projectRepo.findAll();
    }
}