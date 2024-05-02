package dev.alejandro.taskmanager.user.application;


public record UserInput(
        String username,
        String password,
        String role) {
    public static UserInput of(String username, String password, String role) {
        return new UserInput(username, password, role);
    }
}
