package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.model.Project;
import com.taskmanager.taskmanager.model.Status;
import com.taskmanager.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findByProject(Project project);

    // 🔥 DASHBOARD METHODS
    long countByProject(Project project);

    long countByProjectAndStatus(Project project, Status status);
    long countByProjectAndDueDateBefore(Project project, LocalDate date);
}