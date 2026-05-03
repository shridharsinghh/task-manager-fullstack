package com.taskmanager.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")   // ✅ ADD THIS
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "project_id")       // ✅ ADD THIS
    private Project project;
}