package dev.alejandro.taskmanager.user.infrastructure.adapter.http;

import java.io.Serializable;

public record UserResponse(String userId, String username, String role) implements Serializable {
    public static UserResponse of(String userId, String fullName, String role) {
        return new UserResponse(userId, fullName, role);
    }
}
