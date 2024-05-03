package dev.alejandro.taskmanager.common.domain;

import java.util.UUID;

public record Identifier(UUID value) {

    public Identifier {
        if (value == null) {
            throw new InvalidIdentifierException();
        }
    }

    public static Identifier of(UUID value) {
        return new Identifier(value);
    }

    public static Identifier fromString(String value) {
        try {
            return new Identifier(UUID.fromString(value));
        }catch (Exception _) {
            throw new InvalidIdentifierException();
        }
    }

    public static Identifier generate() {
        return new Identifier(UUID.randomUUID());
    }
}
