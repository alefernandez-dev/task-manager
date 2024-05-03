package dev.alejandro.taskmanager.user.infrastructure.adapter.repository;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.User;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.Users;
import dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa.UsersJpa;
import dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa.UsersMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersAdapter implements Users {

    private final UsersJpa repository;

    public UsersAdapter(UsersJpa repository) {
        this.repository = repository;
    }

    @Override
    public void persist(User user) {
        repository.save(UsersMapper.toJpa(user));
    }

    @Override
    public void update(User user) {
        persist(user);
    }

    @Override
    public Optional<User> findById(Identifier userId) {
        return repository
                .findById(userId.value())
                .map(UsersMapper::toUser);
    }

    @Override
    public List<User> findByUserName(UserName username) {
        return repository
                .findByUsernameContainingIgnoreCase(username.value())
                .stream()
                .map(UsersMapper::toUser)
                .toList();
    }

    @Override
    public List<User> findAll() {
        return repository
                .findAll()
                .stream()
                .map(UsersMapper::toUser)
                .toList();
    }

    @Override
    public void deleteById(Identifier userId) {
        repository.deleteById(userId.value());
    }

    @Override
    public boolean existsById(Identifier userId) {
        return repository.existsById(userId.value());
    }

    @Override
    public boolean existsByUsername(UserName username) {
        return repository.existsByUsername(username.value());
    }

    @Override
    public boolean existsByUsernameAndNotId(UserName username, Identifier userId) {
        return repository.existsByUsernameAndIdNot(username.value(), userId.value());
    }

    @Override
    public List<User> findAllById(Set<Identifier> usersId) {
        var ids = usersId.stream().map(Identifier::value).collect(Collectors.toSet());
        return repository
                .findAllById(ids)
                .stream()
                .map(UsersMapper::toUser)
                .toList();
    }

}
