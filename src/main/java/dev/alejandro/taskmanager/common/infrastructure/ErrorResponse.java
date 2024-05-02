package dev.alejandro.taskmanager.common.infrastructure;

import java.io.Serializable;
import java.util.List;

public record ErrorResponse(List<String> errors) implements Serializable {

    public static ErrorResponse single(String error) {
        return new ErrorResponse(List.of(error));
    }

    public static ErrorResponse list(List<String> errors) {
        return new ErrorResponse(errors);
    }
}