package dev.alejandro.taskmanager.user.domain;

public record Password(String value) {
    public Password {
        if(value == null || value.isBlank()) {
            throw new InvalidPasswordException();
        }
    }
    public static Password of(String value) {
        return new Password(value);
    }
}
