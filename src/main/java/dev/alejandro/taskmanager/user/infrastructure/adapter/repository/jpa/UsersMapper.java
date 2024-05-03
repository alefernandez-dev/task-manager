package dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.Password;
import dev.alejandro.taskmanager.user.domain.User;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.UserRole;

public final class UsersMapper {

    public static User toUser(UserJpa userJpa) {
        return new User(
                Identifier.of(userJpa.id),
                UserName.of(userJpa.username),
                Password.of(userJpa.password),
                UserRole.fromString(userJpa.role),
                userJpa.isBusy
        );
    }

    public static UserJpa toJpa(User user) {
        var jpa = new UserJpa();
        jpa.id = user.id().value();
        jpa.username = user.username().value();
        jpa.password = user.password().value();
        jpa.role = user.role().name();
        jpa.isBusy = user.isBusy();
        return jpa;
    }

}
