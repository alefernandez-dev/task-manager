package dev.alejandro.taskmanager.user.application;

import java.util.UUID;

public record UserOutput(
        UUID userId,
        String username,
        String role) {
    public static UserOutput of(UUID userId, String username, String role) {
        return new UserOutput(userId, username, role);
    }
}
