package dev.alejandro.taskmanager.user.infrastructure.adapter.repository;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.Password;
import dev.alejandro.taskmanager.user.domain.User;
import dev.alejandro.taskmanager.user.domain.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                Identifier.of(rs.getObject("id", UUID.class)),
                UserName.of(rs.getString("username")),
                Password.of(rs.getString("password")),
                UserRole.fromString(rs.getString("role"))
        );
    }
}
