package dev.alejandro.taskmanager.task.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record Task(
        Identifier taskId,
        TaskName taskName,
        TaskDescription taskDescription,
        Set<TaskEmployee> employees,
        TaskLifecycle taskLifecycle,
        TaskStatus taskStatus
        ) {

    public Task {
        validate();
    }

    public static Task of(
            TaskName taskName,
            TaskDescription taskDescription,
            Set<TaskEmployee> employees,
            TaskLifecycle taskLifecycle) {
        return new Task(Identifier.generate(), taskName, taskDescription, employees, taskLifecycle, TaskStatus.of(TaskStatus.Status.NOT_STARTED));
    }

    public Task update(TaskName taskName, TaskDescription taskDescription) {
        return new Task(taskId, taskName, taskDescription, employees, taskLifecycle, taskStatus);
    }

    public Task addEmployee(TaskEmployee employee) {
        var updatedEmployess = new HashSet<>(employees);
        updatedEmployess.add(employee);
        return new Task(taskId, taskName, taskDescription, updatedEmployess, taskLifecycle, taskStatus);
    }

    public Task removeEmployee(TaskEmployee employee) {
        var updatedEmployees = new HashSet<>(employees);
        updatedEmployees.remove(employee);
        return new Task(taskId, taskName, taskDescription, updatedEmployees, taskLifecycle, taskStatus);
    }

    public Duration timeRemainingUntilDue() {
        return Duration.between(LocalDateTime.now(), taskLifecycle.dueDate());
    }

    public Task changeStatus(TaskStatus status) {
        return new Task(taskId, taskName, taskDescription, employees, taskLifecycle.completeTask(), status);

    }

    private void validate() {
        Objects.requireNonNull(taskId, "taskId is null");
        Objects.requireNonNull(taskName, "taskName is null");
        Objects.requireNonNull(taskDescription, "taskDescription is null");
        Objects.requireNonNull(employees, "employees is null");
        Objects.requireNonNull(taskLifecycle, "taskLifecycle is null");
        Objects.requireNonNull(taskStatus, "taskStatus is null");
    }

}
