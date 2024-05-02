package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.user.domain.*;

public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public CreateUserUseCase(UserRepository userRepository, PasswordEncryptor passwordEncryptor) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public void create(UserInput userInput) {

        var username = UserName.of(userInput.username());
        if(userRepository.existByUsername(username)) {
            throw new UsernameAlreadyExistsError();
        }

        var encryptedPassword = passwordEncryptor.encrypt(userInput.password());
        var user = User.of(
                username,
                Password.of(encryptedPassword),
                UserRole.fromString(userInput.role())
        );
        userRepository.persist(user);

    }
}
