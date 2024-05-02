package dev.alejandro.taskmanager.common.application;

public class ApplicationException extends RuntimeException{
    private String message;

    public ApplicationException(String message) {
        this.message = message;
    }

    public ApplicationException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
