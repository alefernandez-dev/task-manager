package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AvailableEmployeeFilter {

    private final Users repository;

    public AvailableEmployeeFilter(Users repository) {
        this.repository = repository;
    }

    public Set<User> filterAvailable(Set<Identifier> employeesId) {
        return repository
                .findAllById(employeesId)
                .stream()
                .filter(this::isEmployee)
                .filter(this::isAvailable)
                .collect(Collectors.toSet());
    }

    public Optional<User> filterAvailable(Identifier employeesId) {
        return repository
                .findById(employeesId)
                .filter(this::isEmployee)
                .filter(this::isAvailable);
    }

    private boolean isEmployee(User user) {
        return user.role().value().equals(UserRole.Role.EMPLOYEE);
    }

    private boolean isAvailable(User user) {
        return !user.isBusy();
    }
}
