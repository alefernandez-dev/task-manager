package dev.alejandro.taskmanager.task.domain;

public record TaskDescription(String value) {

    public TaskDescription {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("task taskDescription cannot be null or empty");
        }
    }

    public static TaskDescription of(String value) {
        return new TaskDescription(value);
    }
}
