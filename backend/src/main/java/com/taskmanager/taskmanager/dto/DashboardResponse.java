package com.taskmanager.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardResponse {

    private long totalTasks;
    private long todo;
    private long inProgress;
    private long done;
    private long overdue;
}