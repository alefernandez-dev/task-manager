package dev.alejandro.taskmanager.user.infrastructure.adapter.repository;

import dev.alejandro.taskmanager.common.domain.Identifier;
import dev.alejandro.taskmanager.user.domain.UserName;
import dev.alejandro.taskmanager.user.domain.User;
import dev.alejandro.taskmanager.user.domain.UserRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;
import java.util.Optional;

public class UserH2Repository implements UserRepository {

    private final JdbcClient jdbcClient;

    public UserH2Repository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void persist(User user) {
        jdbcClient
                .sql("insert into users (id, username, password, role) values (:id, :username, :password, :role)")
                .param("id", user.id().value())
                .param("username", user.username().value())
                .param("password", user.password().value())
                .param("role", user.role().toPrimitive())
                .update();

    }

    @Override
    public void update(User user) {
        jdbcClient
                .sql("update users set username = :username, password = :password, role = :role where id = :id")
                .param("username", user.username().value())
                .param("password", user.password().value())
                .param("role", user.role().toPrimitive())
                .param("id", user.id().value())
                .update();
    }

    @Override
    public Optional<User> findById(Identifier userId) {
        try {
            var user = jdbcClient
                    .sql("select * from users where id = :id")
                    .param("id", userId.value())
                    .query(new UserRowMapper())
                    .single();
            return Optional.of(user);
        }catch (Exception _) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findByUserName(UserName userName) {
        return jdbcClient
                .sql("select * from users where username like :username")
                .param("username", "%" + userName.value() + "%")
                .query(new UserRowMapper())
                .list();
    }

    @Override
    public List<User> findAll() {
        return jdbcClient
                .sql("select * from users")
                .query(new UserRowMapper())
                .list();
    }

    @Override
    public void deleteById(Identifier userId) {
        jdbcClient
                .sql("delete from users where id = :id")
                .param("id", userId.value())
                .update();
    }

    @Override
    public boolean existById(Identifier userId) {
        var count = jdbcClient
                .sql("select count(*) from users where id = :id")
                .param("id", userId.value())
                .query(Integer.class)
                .single();
        return count > 0;
    }

    @Override
    public boolean existByUsername(UserName username) {
        var count = jdbcClient
                .sql("select count(*) from users where username = :username")
                .param("username", username.value())
                .query(Integer.class)
                .single();
        return count > 0;
    }

    @Override
    public boolean existByUsernameAndNotId(UserName username, Identifier userId) {
        var count = jdbcClient
                .sql("select count(*) from users where username = :username and id != :id")
                .param("username", username.value())
                .param("id", userId.value())
                .query(Integer.class)
                .single();
        return count > 0;
    }

    @Override
    public boolean isEmployeeNotBusy(Identifier employeeId) {
        var count = jdbcClient
                .sql("""
                        select count(*) from users u
                        join users_tasks ut ON u.id = ut.user_id
                        join tasks t ON ut.task_id = t.id
                        where u.id = :employeeId
                        and t.task_status not in ('COMPLETED', 'FINISHED')
                        """)
                .param("employeeId", employeeId.value())
                .query(Integer.class)
                .single();
        return count > 0;
    }
}
