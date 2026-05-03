package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.DashboardResponse;
import com.taskmanager.taskmanager.model.*;
import com.taskmanager.taskmanager.repository.ProjectRepository;
import com.taskmanager.taskmanager.repository.TaskRepository;
import com.taskmanager.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;
    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    // Create Task
    public Task createTask(String title, String projectId, String assignedToEmail) {

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User user = userRepo.findByEmail(assignedToEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(title)
                .project(project)
                .assignedTo(user)
                .status(Status.TODO)
                .build();

        return taskRepo.save(task);
    }

    // Get tasks by project
    public List<Task> getTasksByProject(String projectId) {

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return taskRepo.findByProject(project);
    }

    // Update task status
    public Task updateStatus(String taskId, Status status) {

        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);

        return taskRepo.save(task);
    }
    public DashboardResponse getDashboard(String projectId) {

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        long total = taskRepo.countByProject(project);
        long todo = taskRepo.countByProjectAndStatus(project, Status.TODO);
        long inProgress = taskRepo.countByProjectAndStatus(project, Status.IN_PROGRESS);
        long done = taskRepo.countByProjectAndStatus(project, Status.DONE);

        long overdue = taskRepo.countByProjectAndDueDateBefore(project, LocalDate.now());

        return new DashboardResponse(total, todo, inProgress, done, overdue);
    }
}