package dev.alejandro.taskmanager.task.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.task.domain.Task;
import dev.alejandro.taskmanager.task.domain.TaskRepository;

import java.util.List;
import java.util.UUID;

public class RetrieveTasksUseCase {
    private final TaskRepository taskRepository;

    public RetrieveTasksUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskOutput> retrieveAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::toOutput)
                .toList();
    }

    public TaskOutput retrieveById(UUID tastId) {
        var id = Identifier.of(tastId);
        return taskRepository.findById(id)
                .map(this::toOutput)
                .orElseThrow(TaskNotFoundException::new);
    }

    private TaskOutput toOutput(Task task) {
        return null;
    }
}
