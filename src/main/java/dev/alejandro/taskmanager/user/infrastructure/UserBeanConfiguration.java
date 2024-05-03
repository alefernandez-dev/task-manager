package dev.alejandro.taskmanager.user.infrastructure;

import dev.alejandro.taskmanager.user.application.CreateUserUseCase;
import dev.alejandro.taskmanager.user.application.DeleteUserByIdUseCase;
import dev.alejandro.taskmanager.user.application.RetrieveUsersUseCase;
import dev.alejandro.taskmanager.user.application.UpdateUserUseCase;
import dev.alejandro.taskmanager.user.domain.AvailableEmployeeFilter;
import dev.alejandro.taskmanager.user.domain.EmployeeBusyMarker;
import dev.alejandro.taskmanager.user.domain.PasswordEncryptor;
import dev.alejandro.taskmanager.user.domain.Users;
import dev.alejandro.taskmanager.user.infrastructure.adapter.PasswordStrongTextEncryptorAdapter;
import dev.alejandro.taskmanager.user.infrastructure.adapter.repository.UsersAdapter;
import dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa.UsersJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfiguration {

    @Bean
    Users users(UsersJpa usersJpa) {
        return new UsersAdapter(usersJpa);
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
    CreateUserUseCase createUserUseCase(Users repository, PasswordEncryptor passwordEncryptor) {
        return new CreateUserUseCase(repository, passwordEncryptor);
    }

    @Bean
    DeleteUserByIdUseCase deleteUserByIdUseCase(Users repository) {
        return new DeleteUserByIdUseCase(repository);
    }

    @Bean
    RetrieveUsersUseCase retrieveUsersUseCase(Users repository) {
        return new RetrieveUsersUseCase(repository);
    }

    @Bean
    UpdateUserUseCase updateUserUseCase(Users repository) {
        return new UpdateUserUseCase(repository);
    }

    @Bean
    AvailableEmployeeFilter availableEmployeeFilter(Users repository) {
        return new AvailableEmployeeFilter(repository);
    }

    @Bean
    EmployeeBusyMarker employeeBusyMarker(Users repository) {
        return new EmployeeBusyMarker(repository);
    }

}
