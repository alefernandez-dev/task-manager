package dev.alejandro.taskmanager.user.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Users {
    void persist(User user);
    void update(User user);
    Optional<User> findById(Identifier userId);
    List<User> findByUserName(UserName username);
    List<User> findAll();
    void deleteById(Identifier  userId);
    boolean existsById(Identifier userId);
    boolean existsByUsername(UserName username);
    boolean existsByUsernameAndNotId(UserName username, Identifier userId);
    List<User> findAllById(Set<Identifier> usersId);
}
