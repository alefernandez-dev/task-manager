package dev.alejandro.taskmanager.task.infrastructure.adapter.repository;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.task.domain.Task;
import dev.alejandro.taskmanager.task.domain.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskH2Repository implements TaskRepository {
    @Override
    public void persist(Task task) {

    }

    @Override
    public boolean existsById(Identifier id) {
        return false;
    }

    @Override
    public void deleteById(Identifier id) {

    }

    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
    public Optional<Task> findById(Identifier taskId) {
        return Optional.empty();
    }

    @Override
    public void update(Task task) {

    }
}
