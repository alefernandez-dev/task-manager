package dev.alejandro.taskmanager.common.domain;

public class DomainException extends RuntimeException{
    private String message;

    public DomainException(String message) {
        this.message = message;
    }

    public DomainException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
