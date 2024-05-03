package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AvailableEmployeeFilterTest {

    @Mock
    private Users repositoryMock;

    @InjectMocks
    AvailableEmployeeFilter filter;

    List<User> employeesList01;
    List<User> employeesList02;
    List<User> employeesList03;
    List<User> employeesList04;
    List<User> employeesList05;

    @BeforeEach
    void setUp() {
        employeesList01 = List.of(
                new User(Identifier.generate(),UserName.of("alejandro"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false),
                new User(Identifier.generate(),UserName.of("cesar123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false),
                new User(Identifier.generate(),UserName.of("mariana123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false),
                new User(Identifier.generate(),UserName.of("pedro123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false)
        );
        employeesList02 = List.of(
                new User(Identifier.generate(),UserName.of("alejandro"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false),
                new User(Identifier.generate(),UserName.of("cesar123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true),
                new User(Identifier.generate(),UserName.of("mariana123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false),
                new User(Identifier.generate(),UserName.of("pedro123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true)
        );

        employeesList03 = List.of(
                new User(Identifier.generate(),UserName.of("alejandro"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true),
                new User(Identifier.generate(),UserName.of("cesar123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true),
                new User(Identifier.generate(),UserName.of("mariana123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true),
                new User(Identifier.generate(),UserName.of("pedro123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true)
        );

        employeesList04 = List.of(
                new User(Identifier.generate(),UserName.of("alejandro"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false),
                new User(Identifier.generate(),UserName.of("cesar123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), false),
                new User(Identifier.generate(),UserName.of("mariana123"), Password.of("12345678"), UserRole.fromString("ADMIN"), true),
                new User(Identifier.generate(),UserName.of("pedro123"), Password.of("12345678"), UserRole.fromString("EMPLOYEE"), true)
        );

        employeesList05 = List.of(
                new User(Identifier.generate(),UserName.of("alejandro"), Password.of("12345678"), UserRole.fromString("ADMIN"), true),
                new User(Identifier.generate(),UserName.of("cesar123"), Password.of("12345678"), UserRole.fromString("ADMIN"), true),
                new User(Identifier.generate(),UserName.of("mariana123"), Password.of("12345678"), UserRole.fromString("ADMIN"), true),
                new User(Identifier.generate(),UserName.of("pedro123"), Password.of("12345678"), UserRole.fromString("ADMIN"), true)
        );

    }

    @Test
    void allAvailable() {
        var ids = employeesList01.stream().map(User::id).collect(Collectors.toSet());
        when(repositoryMock.findAllById(ids)).thenReturn(employeesList01);

        var employees = filter.filterAvailable(ids);

        assertEquals(employeesList01.size(), employees.size());

    }

    @Test
    void onlyTwoAvailable() {
        var ids = employeesList02.stream().map(User::id).collect(Collectors.toSet());
        when(repositoryMock.findAllById(ids)).thenReturn(employeesList02);

        var employees = filter.filterAvailable(ids);

        assertEquals(2, employees.size());
    }

    @Test
    void noneAvailable() {
        var ids = employeesList03.stream().map(User::id).collect(Collectors.toSet());
        when(repositoryMock.findAllById(ids)).thenReturn(employeesList03);

        var employees = filter.filterAvailable(ids);

        assertEquals(0, employees.size());
    }

    @Test
    void someUserAdminAndSomeEmployeeBusy() {
        var ids = employeesList04.stream().map(User::id).collect(Collectors.toSet());
        when(repositoryMock.findAllById(ids)).thenReturn(employeesList04);

        var employees = filter.filterAvailable(ids);

        assertEquals(2, employees.size());
    }

    @Test
    void allUsersAreAdmin() {
        var ids = employeesList05.stream().map(User::id).collect(Collectors.toSet());
        when(repositoryMock.findAllById(ids)).thenReturn(employeesList05);

        var employees = filter.filterAvailable(ids);

        assertEquals(0, employees.size());
    }

}