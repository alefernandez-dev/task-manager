package dev.alejandro.taskmanager.task.infrastructure.adapter.repository.jpa;

import dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa.UserJpa;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TASKS")
public class TaskJpa {
    @Id UUID id;
    String name;
    String description;
    @ManyToMany
    @JoinTable(
            name = "USERS_TASKS",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<UserJpa> employees;
    LocalDate creationDate;
    LocalDate dueDate;
    LocalDate completionDate;
    String taskStatus;
}
