package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.model.Project;
import com.taskmanager.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // Create Project
    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestParam String name,
            @RequestHeader("X-User-Email") String email
    ) {
        return ResponseEntity.ok(projectService.createProject(name, email));
    }

    // Get all projects of user
    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestHeader("X-User-Email") String email
    ) {
        return ResponseEntity.ok(projectService.getUserProjects(email));
    }
}