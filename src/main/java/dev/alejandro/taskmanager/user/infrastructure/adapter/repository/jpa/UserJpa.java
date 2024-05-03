package dev.alejandro.taskmanager.user.infrastructure.adapter.repository.jpa;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "USERS")
public class UserJpa {
    @Id UUID id;
    String username;
    String password;
    String role;
    boolean isBusy;

    public void setData(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
