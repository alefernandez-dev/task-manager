package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeBusyMarkerTest {

    @Mock
    private Users repositoryMock;
    @InjectMocks
    private EmployeeBusyMarker busyMarker;

    @Test
    void markBusySuccessfully() {

        var employee = new User(Identifier.generate(), UserName.of("ale123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false);

        when(repositoryMock.findById(employee.id())).thenReturn(Optional.of(employee));

        assertDoesNotThrow(() -> busyMarker.markBusy(employee.id()));


    }

    @Test
    void markBusyThrowsExceptions() {
        var employeeId = Identifier.of(UUID.randomUUID());

        when(repositoryMock.findById(employeeId)).thenReturn(Optional.empty());
        assertThrowsExactly(EmployeeNotFoundException.class, () -> busyMarker.markBusy(employeeId));



        var employee01 = new User(Identifier.generate(), UserName.of("ale123"), Password.of("12345678"), UserRole.fromString("ADMIN"), true);

        when(repositoryMock.findById(employee01.id())).thenReturn(Optional.of(employee01));

        assertThrowsExactly(UserNotIsEmployeeException.class, () -> busyMarker.markBusy(employee01.id()));


        var employee02 = new User(Identifier.generate(), UserName.of("ale123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true);

        when(repositoryMock.findById(employee02.id())).thenReturn(Optional.of(employee02));

        assertThrowsExactly(EmployeeAlreadyBusyException.class, () -> busyMarker.markBusy(employee02.id()));


    }
}