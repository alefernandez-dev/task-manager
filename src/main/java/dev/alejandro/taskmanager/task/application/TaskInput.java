package dev.alejandro.taskmanager.task.application;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record TaskInput(String taskName, String taskDescription, Set<UUID> employeesId, LocalDate dueDate) {
    public static TaskInput of(String taskName, String taskDescription, Set<UUID> employeesId, LocalDate dueDate) {
        return new TaskInput(taskName, taskDescription, employeesId, dueDate);
    }
}
