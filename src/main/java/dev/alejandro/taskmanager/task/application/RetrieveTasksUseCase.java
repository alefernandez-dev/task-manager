package dev.alejandro.taskmanager.task.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.task.domain.Task;
import dev.alejandro.taskmanager.task.domain.Tasks;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RetrieveTasksUseCase {
    private final Tasks taskRepository;

    public RetrieveTasksUseCase(Tasks taskRepository) {
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
        return new TaskOutput(
                task.taskId().value(),
                task.taskName().value(),
                task.taskDescription().value(),
                task.taskLifecycle().creationDate(),
                task.taskLifecycle().dueDate(),
                task.taskLifecycle().completionDate(),
                task.employees().stream().map(e -> new TaskOutput.Employee(e.id(), e.name())).collect(Collectors.toSet()),
                task.taskStatus().name()
        );
    }
}
