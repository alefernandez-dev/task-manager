package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;

import java.util.Set;

public class EmployeeBusyMarker {

    private final Users repository;

    public EmployeeBusyMarker(Users repository) {
        this.repository = repository;
    }

    public void markBusy(Identifier employeeId) {

        var employee = repository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);

        if(!isEmployee(employee)) {
            throw new UserNotIsEmployeeException();
        }
        if(employee.isBusy()) {
            throw new EmployeeAlreadyBusyException();
        }
        var employeeBusied = employee.markAsOccupied();
        repository.persist(employeeBusied);
    }

    private boolean isEmployee(User user) {
        return user.role().value() == UserRole.Role.EMPLOYEE;
    }

    public void markBusy(Set<Identifier> employeesId) {
        employeesId.forEach(this::markBusy);
    }

}
