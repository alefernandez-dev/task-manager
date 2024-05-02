package dev.alejandro.taskmanager.user.domain;

public record UserName(String value) {
    public UserName {
        if(value == null || !value.matches("^[a-zA-Z0-9]+$")) {
            throw new InvalidUsernameException();
        }
    }
    public static UserName of(String value) {
        return new UserName(value);
    }
}
