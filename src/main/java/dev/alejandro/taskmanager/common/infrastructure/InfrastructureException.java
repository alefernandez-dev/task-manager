package dev.alejandro.taskmanager.common.infrastructure;

public class InfrastructureException extends RuntimeException{
    private String message;

    public InfrastructureException(String message) {
        this.message = message;
    }

    public InfrastructureException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
