package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;
import java.util.Objects;

// TODO: Fields name and username
public record User(
        Identifier id,
        UserName username,
        Password password,
        UserRole role,
        boolean isBusy) {

    public static User of(UserName userName, Password password, UserRole role) {
        return new User(Identifier.generate(), userName, password, role, false);
    }

    public User update(UserName name, Password password) {
        return new User(id, name, password, role, isBusy);
    }

    public User changeRole(UserRole userRole) {
        return new User(id, username, password ,userRole, isBusy);
    }

    public User markAsOccupied() {
        return new User(id, username, password ,role, true);
    }

    public User markAsAvailable() {
        return new User(id, username, password ,role, false);
    }

    public User {
        Objects.requireNonNull(id);
        Objects.requireNonNull(username );
        Objects.requireNonNull(password);
        Objects.requireNonNull(role);
    }
}
