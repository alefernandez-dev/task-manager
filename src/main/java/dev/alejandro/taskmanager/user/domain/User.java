package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;
import java.util.Objects;


public record User(
        Identifier id,
        UserName username,
        Password password,
        UserRole role) {

    public static User of(UserName userName, Password password, UserRole role) {
        return new User(Identifier.generate(), userName, password, role);
    }

    public User update(UserName name, Password password) {
        return new User(id, name, password, role);
    }

    public User changeRole(UserRole userRole) {
        return new User(id, username, password ,userRole);
    }

    public User {
        Objects.requireNonNull(id);
        Objects.requireNonNull(username );
        Objects.requireNonNull(password);
        Objects.requireNonNull(role);
    }
}
