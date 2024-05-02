package dev.alejandro.taskmanager.user.infrastructure.adapter.http;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UserRequest(
        @NotNull(message = "username is required")
        @Size(max = 100, message = "username cannot be longer than 100 characters")
        @Pattern(regexp = "\\S+", message = "username cannot contain whitespace")
        String username,
        @NotNull(message = "password is required")
        @Size(min = 6, max = 100 , message = "password must be between 6 and 100 characters long")
        @Pattern(regexp = "\\S+", message = "password cannot contain whitespace")
        String password,
        @NotNull(message = "role is required")
        @Pattern(regexp = "^(?i)(ADMIN|EMPLOYEE)$", message = "role must be either ADMIN or TECH (case-insensitive)")
        String role
) implements Serializable {}
