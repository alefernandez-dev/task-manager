package dev.alejandro.taskmanager.user.application;

import dev.alejandro.taskmanager.user.domain.*;

public class CreateUserUseCase {

    private final Users repository;
    private final PasswordEncryptor passwordEncryptor;

    public CreateUserUseCase(Users userRepository, PasswordEncryptor passwordEncryptor) {
        this.repository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public void create(UserInput userInput) {

        var username = UserName.of(userInput.username());
        if(repository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsError();
        }

        var encryptedPassword = passwordEncryptor.encrypt(userInput.password());
        var user = User.of(
                username,
                Password.of(encryptedPassword),
                UserRole.fromString(userInput.role())
        );
        repository.persist(user);

    }
}
