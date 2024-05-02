package dev.alejandro.taskmanager.task.domain;

public record TaskName(String value) {

    public TaskName {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("task taskName cannot be null or empty");
        }
    }

    public static TaskName of(String value) {
        return new TaskName(value);
    }

}
