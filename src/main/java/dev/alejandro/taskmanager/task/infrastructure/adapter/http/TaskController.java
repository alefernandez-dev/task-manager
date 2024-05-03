package dev.alejandro.taskmanager.task.infrastructure.adapter.http;

import dev.alejandro.taskmanager.task.application.RetrieveTasksUseCase;
import dev.alejandro.taskmanager.task.application.TaskOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final RetrieveTasksUseCase retrieveTasksUseCase;

    public TaskController(RetrieveTasksUseCase retrieveTasksUseCase) {
        this.retrieveTasksUseCase = retrieveTasksUseCase;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> list() {
        var tasks = retrieveTasksUseCase.retrieveAll().stream().map(this::toResponse).toList();
        return ResponseEntity.ok(tasks);
    }

    // TODO: Mapper!!
    private TaskResponse toResponse(TaskOutput taskOutput) {
        return new TaskResponse(
                taskOutput.taskId().toString(),
                taskOutput.name(),
                taskOutput.description(),
                taskOutput.employees().stream().map(e -> new TaskResponse.Employee(e.employeeId().toString(), e.name())).collect(Collectors.toSet()),
                taskOutput.creationDate(),
                taskOutput.dueDate(),
                taskOutput.completionDate(),
                taskOutput.status()
        );
    }

}
