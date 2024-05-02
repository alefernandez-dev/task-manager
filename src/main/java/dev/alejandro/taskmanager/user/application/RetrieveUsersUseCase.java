package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.User;
import dev.alejandro.taskmanager.user.domain.UserRepository;

import java.util.List;

public class RetrieveUsersUseCase {
    private final UserRepository userRepository;

    public RetrieveUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput retrieveById(String userId) {
        return userRepository.findById(Identifier.fromString(userId))
                .map(this::toUserOutput)
                .orElseThrow(UserNotFoundException::new);
    }
    public List<UserOutput> retrieveByName(String username) {
        return userRepository
                .findByUserName(UserName.of(username))
                .stream()
                .map(this::toUserOutput)
                .toList();
    }

    public List<UserOutput> retrieveAll() {
        return userRepository
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
