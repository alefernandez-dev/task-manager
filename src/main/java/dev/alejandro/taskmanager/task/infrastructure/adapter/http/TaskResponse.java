package dev.alejandro.taskmanager.task.infrastructure.adapter.http;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public record TaskResponse(String taskId,
                           String name,
                           String description,
                           Set<Employee> employees,
                           LocalDate creationDate,
                           LocalDate dueDate,
                           LocalDate completionDate,
                           String status) implements Serializable {

    public record Employee(String employeeId, String name) {}

}
