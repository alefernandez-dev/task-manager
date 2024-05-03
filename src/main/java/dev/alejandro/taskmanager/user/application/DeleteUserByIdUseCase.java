package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.Users;

public class DeleteUserByIdUseCase {
    private final Users repository;

    public DeleteUserByIdUseCase(Users userRepository) {
        this.repository = userRepository;
    }

    public void delete(String userId) {
        var id = Identifier.fromString(userId);
        if (!repository.existsById(id)) {
            throw new UserNotFoundException();
        }
        repository.deleteById(id);
    }
}
