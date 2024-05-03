package dev.alejandro.taskmanager.task.domain;

import dev.alejandro.taskmanager.common.domain.Identifier;

import java.util.List;
import java.util.Optional;

public interface Tasks {
    void persist(Task task);
    boolean existsById(Identifier id);
    void deleteById(Identifier id);
    List<Task> findAll();
    Optional<Task> findById(Identifier taskId);
    void update(Task task);
}
