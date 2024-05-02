package dev.alejandro.taskmanager.task.infrastructure.adapter.repository;

import dev.alejandro.taskmanager.task.domain.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
