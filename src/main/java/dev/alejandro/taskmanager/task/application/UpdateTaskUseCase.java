package dev.alejandro.taskmanager.task.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.task.domain.*;

import java.util.UUID;

public class UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public UpdateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void update(String name, String description, UUID taskId) {
        var task = getTask(Identifier.of(taskId));
        var taskUpdated = task.update(TaskName.of(name), TaskDescription.of(description));
        taskRepository.update(task);
    }

    public void changeStatus(String newStatus, UUID taskId) {
        var task = getTask(Identifier.of(taskId));
        var taskUpdated =  task.changeStatus(TaskStatus.fromString(newStatus));
        taskRepository.update(taskUpdated);
    }

    public void addEmployee(UUID employeeId, UUID taskId) {
        var task = getTask(Identifier.of(taskId));
        var taskUpdated = task.addEmployee(Identifier.of(employeeId));
        taskRepository.update(task);
    }

    public void removeEmployee(UUID employeeId, UUID taskId) {
        var task = getTask(Identifier.of(taskId));
        var taskUpdated = task.removeEmployee(Identifier.of(employeeId));
        taskRepository.update(task);
    }

    private Task getTask(Identifier taskId) {
        return taskRepository
                .findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
    }

}
