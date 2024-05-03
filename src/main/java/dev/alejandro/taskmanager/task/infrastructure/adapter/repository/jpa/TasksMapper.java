package dev.alejandro.taskmanager.task.infrastructure.adapter.repository.jpa;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.task.domain.*;
import dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa.UserJpa;

import java.util.stream.Collectors;

public final class TasksMapper {

    public static Task toTask(TaskJpa jpa) {
        return new Task(
                Identifier.of(jpa.id),
                TaskName.of(jpa.name),
                TaskDescription.of(jpa.description),
                jpa.employees.stream().map(TasksMapper::toEmployee).collect(Collectors.toSet()),
                new TaskLifecycle(jpa.creationDate, jpa.dueDate, jpa.completionDate),
                TaskStatus.fromString(jpa.taskStatus)
        );
    }

    public static TaskJpa toJpa(Task task) {
        var jpa = new TaskJpa();
        jpa.id = task.taskId().value();
        jpa.name = task.taskName().value();
        jpa.description = task.taskDescription().value();
        jpa.employees = task.employees().stream().map(TasksMapper::toUserJpa).collect(Collectors.toSet());
        jpa.creationDate = task.taskLifecycle().creationDate();
        jpa.dueDate = task.taskLifecycle().dueDate();
        jpa.completionDate = task.taskLifecycle().completionDate();
        jpa.taskStatus = task.taskStatus().name();
        return jpa;
    }

    private static TaskEmployee toEmployee(UserJpa jpa) {
        return new TaskEmployee(jpa.getId(), jpa.getUsername());
    }

    private static UserJpa toUserJpa(TaskEmployee employee) {
        var jpa = new UserJpa();
        jpa.setData(employee.id(), employee.name());
        return jpa;
    }
}
