package dev.alejandro.taskmanager.task.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.task.domain.*;
import dev.alejandro.taskmanager.user.domain.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RegisterNewTaskUseCase {

    private final Tasks taskRepository;
    private final AvailableEmployeeFilter filter;
    private final EmployeeBusyMarker busyMarker;

    public RegisterNewTaskUseCase(Tasks taskRepository, AvailableEmployeeFilter filter, EmployeeBusyMarker busyMarker) {
        this.taskRepository = taskRepository;
        this.filter = filter;
        this.busyMarker = busyMarker;
    }

    public void create(TaskInput taskInput) {

        var employeesId = getEmployeeIdentifier(taskInput);

        var employees = getEmployeesAvailable(employeesId);

        var task = createTask(taskInput, employees);

        busyMarker.markBusy(employeesId);

        taskRepository.persist(task);
    }

    private Set<TaskEmployee> toTaskEmployees(Set<User> users){
        return users
                .stream()
                .map(e -> new TaskEmployee(e.id().value(), e.username().value()))
                .collect(Collectors.toSet());
    }

    private Set<Identifier> getEmployeeIdentifier(TaskInput taskInput) {
        return taskInput.employeesId().stream().map(Identifier::new).collect(Collectors.toSet());
    }

    private Task createTask(TaskInput taskInput, Set<User> employees) {
        return Task.of(
                TaskName.of(taskInput.taskName()),
                TaskDescription.of(taskInput.taskDescription()),
                toTaskEmployees(employees),
                TaskLifecycle.of(taskInput.dueDate())
        );
    }

    private Set<User> getEmployeesAvailable(Set<Identifier> ids) {
        var employees = filter.filterAvailable(ids);
        if (employees.isEmpty()) {
            throw new EmployeesForTaskNotFoundException();
        }
        return employees;
    }
}
