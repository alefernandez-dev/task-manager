package dev.alejandro.taskmanager.task.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record Task(
        Identifier taskId,
        TaskName taskName,
        TaskDescription taskDescription,
        Set<Identifier> employeesId,
        TaskLifecycle taskLifecycle,
        TaskStatus taskStatus
        ) {

    public Task {
        validate();
    }

    public static Task of(
            TaskName taskName,
            TaskDescription taskDescription,
            Set<Identifier> employeesId,
            TaskLifecycle taskLifecycle) {
        return new Task(Identifier.generate(), taskName, taskDescription, employeesId, taskLifecycle, TaskStatus.of(TaskStatus.Status.NOT_STARTED));
    }

    public Task update(TaskName taskName, TaskDescription taskDescription) {
        return new Task(taskId, taskName, taskDescription, employeesId, taskLifecycle, taskStatus);
    }

    public Task addEmployee(Identifier employeeId) {
        var updatedTechniciansId = new HashSet<>(employeesId);
        updatedTechniciansId.add(employeeId);
        return new Task(taskId, taskName, taskDescription, updatedTechniciansId, taskLifecycle, taskStatus);
    }

    public Task removeEmployee(Identifier employeeId) {
        var updatedTechniciansId = new HashSet<>(employeesId);
        updatedTechniciansId.remove(employeeId);
        return new Task(taskId, taskName, taskDescription, updatedTechniciansId, taskLifecycle, taskStatus);
    }

    public Duration timeRemainingUntilDue() {
        return Duration.between(LocalDateTime.now(), taskLifecycle.dueDate());
    }

    public Task changeStatus(TaskStatus status) {
        return new Task(taskId, taskName, taskDescription, employeesId, taskLifecycle.completeTask(), status);

    }

    private void validate() {
        Objects.requireNonNull(taskId, "taskId is null");
        Objects.requireNonNull(taskName, "taskName is null");
        Objects.requireNonNull(taskDescription, "taskDescription is null");
        Objects.requireNonNull(employeesId, "employeesId is null");
        Objects.requireNonNull(taskLifecycle, "taskLifecycle is null");
        Objects.requireNonNull(taskStatus, "taskStatus is null");
    }

}
