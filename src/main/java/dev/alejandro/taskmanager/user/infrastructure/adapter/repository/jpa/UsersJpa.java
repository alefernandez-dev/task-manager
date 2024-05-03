package dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsersJpa extends JpaRepository<UserJpa, UUID> {
    List<UserJpa> findByUsernameContainingIgnoreCase(String username);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, UUID id);
}
