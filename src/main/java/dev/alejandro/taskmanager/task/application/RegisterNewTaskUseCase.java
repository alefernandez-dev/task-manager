package dev.alejandro.taskmanager.task.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.task.domain.*;
import dev.alejandro.taskmanager.user.domain.UserRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RegisterNewTaskUseCase {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public RegisterNewTaskUseCase(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void create(TaskInput taskInput) {

        var employees = verifyEmployeesNotBusy(taskInput.employeesId());

        if (employees.isEmpty()) {
            throw new EmployeesForTaskNotFoundException();
        }

        var task = Task.of(
          TaskName.of(taskInput.taskName()),
          TaskDescription.of(taskInput.taskDescription()),
          employees,
          TaskLifecycle.of(taskInput.dueDate())
        );

        taskRepository.persist(task);
    }

    private Set<Identifier> verifyEmployeesNotBusy(Set<UUID> employeesId) {
        return employeesId
                .stream()
                .map(Identifier::new)
                .filter(userRepository::isEmployeeNotBusy)
                .collect(Collectors.toSet());
    }
}
