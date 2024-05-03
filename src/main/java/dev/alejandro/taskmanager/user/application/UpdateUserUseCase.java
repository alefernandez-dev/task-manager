package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.Password;
import dev.alejandro.taskmanager.user.domain.Users;
import dev.alejandro.taskmanager.user.domain.UserRole;

public class UpdateUserUseCase {

    private final Users repository;

    public UpdateUserUseCase(Users userRepository) {
        this.repository = userRepository;
    }

    public void update(UserInput userInput, String userId) {
        var username = UserName.of(userInput.username());
        var id = Identifier.fromString(userId);
        if(repository.existsByUsernameAndNotId(username, id)) {
            throw new UsernameAlreadyExistsError();
        }

        var userUpdated = repository
                .findById(id)
                .orElseThrow(UserNotFoundException::new)
                .update(username, Password.of(userInput.password()));

        repository.update(userUpdated);
    }

    public void changeRole(String role, String userId) {
        var userUpdated = repository
                .findById(Identifier.fromString(userId))
                .orElseThrow(UserNotFoundException::new)
                .changeRole(UserRole.fromString(role));
        repository.update(userUpdated);
    }
}
