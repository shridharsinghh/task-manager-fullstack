package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.DashboardResponse;
import com.taskmanager.taskmanager.model.Status;
import com.taskmanager.taskmanager.model.Task;
import com.taskmanager.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // Create Task
    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestParam String title,
            @RequestParam String projectId,
            @RequestParam String assignedTo
    ) {
        return ResponseEntity.ok(
                taskService.createTask(title, projectId, assignedTo)
        );
    }

    // Get tasks by project
    @GetMapping("/{projectId}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable String projectId) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId));
    }

    // Update status
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateStatus(
            @PathVariable String taskId,
            @RequestParam Status status
    ) {
        return ResponseEntity.ok(taskService.updateStatus(taskId, status));
    }
    @GetMapping("/dashboard/{projectId}")
    public ResponseEntity<DashboardResponse> getDashboard(@PathVariable String projectId) {
        return ResponseEntity.ok(taskService.getDashboard(projectId));
    }
}