package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.Password;
import dev.alejandro.taskmanager.user.domain.UserRepository;
import dev.alejandro.taskmanager.user.domain.UserRole;

public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(UserInput userInput, String userId) {
        var username = UserName.of(userInput.username());
        var id = Identifier.fromString(userId);
        if(userRepository.existByUsernameAndNotId(username, id)) {
            throw new UsernameAlreadyExistsError();
        }

        var userUpdated = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new)
                .update(username, Password.of(userInput.password()));

        userRepository.update(userUpdated);
    }

    public void changeRole(String role, String userId) {
        var userUpdated = userRepository
                .findById(Identifier.fromString(userId))
                .orElseThrow(UserNotFoundException::new)
                .changeRole(UserRole.fromString(role));
        userRepository.update(userUpdated);
    }
}
