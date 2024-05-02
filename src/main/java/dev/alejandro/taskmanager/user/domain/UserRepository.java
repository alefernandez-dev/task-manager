package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void persist(User user);
    void update(User user);
    Optional<User> findById(Identifier userId);
    List<User> findByUserName(UserName username);
    List<User> findAll();
    void deleteById(Identifier  userId);
    boolean existById(Identifier userId);
    boolean existByUsername(UserName username);
    boolean existByUsernameAndNotId(UserName username, Identifier userId);
    boolean isEmployeeNotBusy(Identifier employeeId);
}
