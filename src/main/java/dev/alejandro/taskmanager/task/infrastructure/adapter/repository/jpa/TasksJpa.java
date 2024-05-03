package dev.alejandro.taskmanager.task.infrastructure.adapter.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TasksJpa extends JpaRepository<TaskJpa, UUID> {
}
