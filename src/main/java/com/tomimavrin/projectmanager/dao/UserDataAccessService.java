package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createUser(UUID id, User user) {
        final String sql = "INSERT INTO USERS (id, name, email,boards_id) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id,user.getName(), user.getEmail(), user.getBoards());
    }

    @Override
    public Optional<Ticket> getUser(UUID userId) {
        return Optional.empty();
    }

    @Override
    public int editUser(UUID userId) {
        return 0;
    }

    @Override
    public int deleteUser(UUID userId) {
        return 0;
    }
}
