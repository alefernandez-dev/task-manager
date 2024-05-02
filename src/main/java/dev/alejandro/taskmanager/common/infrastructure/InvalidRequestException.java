package dev.alejandro.taskmanager.common.infrastructure;

import java.util.List;

public class InvalidRequestException extends InfrastructureException{
    private final List<String> errors;

    public InvalidRequestException(List<String> errors) {
        this.errors = errors;
    }

    public static InvalidRequestException addMessages(List<String> errors) {
        return new InvalidRequestException(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}
