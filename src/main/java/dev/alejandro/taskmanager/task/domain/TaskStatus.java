package dev.alejandro.taskmanager.task.domain;


public record TaskStatus(Status value) {

    public TaskStatus {
        if (value == null) {
            throw new IllegalArgumentException("task taskStatus can not be null");
        }
    }

    public static TaskStatus of(Status value) {
        return new TaskStatus(value);
    }

    public static TaskStatus fromString(String value) {
        try {
            return new TaskStatus(Status.valueOf(value.toUpperCase()));
        }catch (Exception _) {
            throw new IllegalArgumentException("invalid value for task taskStatus");
        }
    }

    public enum Status {
        NOT_STARTED,
        IN_PROGRESS,
        PAUSED,
        FINISHED,
        COMPLETED
    }
}
