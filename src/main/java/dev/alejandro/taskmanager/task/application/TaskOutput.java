package dev.alejandro.taskmanager.task.application;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record TaskOutput(
        UUID taskId,
        String name,
        String description,
        LocalDate creationDate,
        LocalDate dueDate,
        LocalDate completionDate,
        Set<Employee> employees,
        String status
) {
    public record Employee(UUID employeeId, String name){}
}
