package dev.alejandro.taskmanager.task.domain;

import java.time.LocalDate;

public record TaskLifecycle(
        LocalDate creationDate,
        LocalDate dueDate,
        LocalDate completionDate) {

    public TaskLifecycle {
        if (dueDate.isBefore(creationDate)) {
            throw new IllegalArgumentException("the due date cannot be earlier than the current date");
        }
    }

    public static TaskLifecycle of(LocalDate dueDate) {
        return new TaskLifecycle(LocalDate.now(), dueDate, null);
    }

    public TaskLifecycle completeTask() {
        return new TaskLifecycle(creationDate, dueDate, LocalDate.now());
    }
}
