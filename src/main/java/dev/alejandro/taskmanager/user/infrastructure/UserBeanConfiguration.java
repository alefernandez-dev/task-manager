package dev.alejandro.taskmanager.user.infrastructure;

import dev.alejandro.taskmanager.user.application.CreateUserUseCase;
import dev.alejandro.taskmanager.user.application.DeleteUserByIdUseCase;
import dev.alejandro.taskmanager.user.application.RetrieveUsersUseCase;
import dev.alejandro.taskmanager.user.application.UpdateUserUseCase;
import dev.alejandro.taskmanager.user.domain.PasswordEncryptor;
import dev.alejandro.taskmanager.user.domain.UserRepository;
import dev.alejandro.taskmanager.user.infrastructure.adapter.PasswordStrongTextEncryptorAdapter;
import dev.alejandro.taskmanager.user.infrastructure.adapter.repository.UserH2Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
public class UserBeanConfiguration {

    @Bean
    UserRepository userRepository(JdbcClient jdbcClient) {
        return new UserH2Repository(jdbcClient);
    }

    @Bean
    PasswordEncryptor passwordEncryptor() {
        return new PasswordStrongTextEncryptorAdapter();
    }

    @Bean
    UserRequestValidator userRequestValidator() {
        return new UserRequestValidator();
    }

    @Bean
    CreateUserUseCase createUserUseCase(UserRepository repository, PasswordEncryptor passwordEncryptor) {
        return new CreateUserUseCase(repository, passwordEncryptor);
    }

    @Bean
    DeleteUserByIdUseCase deleteUserByIdUseCase(UserRepository repository) {
        return new DeleteUserByIdUseCase(repository);
    }

    @Bean
    RetrieveUsersUseCase retrieveUsersUseCase(UserRepository repository) {
        return new RetrieveUsersUseCase(repository);
    }

    @Bean
    UpdateUserUseCase updateUserUseCase(UserRepository repository) {
        return new UpdateUserUseCase(repository);
    }

}
