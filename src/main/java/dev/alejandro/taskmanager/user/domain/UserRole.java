package dev.alejandro.taskmanager.user.domain;

public record UserRole(Role value) {

    public UserRole {
        if (value == null) {
            throw new InvalidUserRoleException();
        }
    }

    public static UserRole fromString(String value) {
        try {
            return new UserRole(Role.valueOf(value.toUpperCase()));
        }catch (Exception _) {
            throw new InvalidUserRoleException();
        }
    }

    public String toPrimitive() {
        return value.name();
    }

    public enum Role {
        ADMIN,
        EMPLOYEE
    }

    public String name() {
        return value.name();
    }
}
