package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.UserRepository;

public class DeleteUserByIdUseCase {
    private final UserRepository userRepository;

    public DeleteUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(String userId) {
        var id = Identifier.fromString(userId);
        if (!userRepository.existById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
