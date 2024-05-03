package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.User;
import dev.alejandro.taskmanager.user.domain.Users;

import java.util.List;

public class RetrieveUsersUseCase {
    private final Users repository;

    public RetrieveUsersUseCase(Users userRepository) {
        this.repository = userRepository;
    }

    public UserOutput retrieveById(String userId) {
        return repository.findById(Identifier.fromString(userId))
                .map(this::toUserOutput)
                .orElseThrow(UserNotFoundException::new);
    }
    public List<UserOutput> retrieveByName(String username) {
        return repository
                .findByUserName(UserName.of(username))
                .stream()
                .map(this::toUserOutput)
                .toList();
    }

    public List<UserOutput> retrieveAll() {
        return repository
                .findAll()
                .stream()
                .map(this::toUserOutput)
                .toList();
    }

    private UserOutput toUserOutput(User user) {
        return UserOutput.of(
                user.id().value(),
                user.username().value(),
                user.role().value().name()
        );
    }
}
