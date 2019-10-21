package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository("users")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public PasswordEncoder passEncoder(){
        return new BCryptPasswordEncoder();
    }

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createUser(UUID id, User user) {
        final String sql = "INSERT INTO USERS (id, email, password, name, enabled) VALUES(?, ?, ?, ?, ?)";
        final String auth = "INSERT INTO AUTHORITIES (email, authority) VALUES (?, ?)";
        String code = passEncoder().encode(user.getPassword());
        jdbcTemplate.update(auth,  user.getEmail(), "USER");
        return jdbcTemplate.update(sql , id, user.getEmail(), code, user.getName(), true);
    }

    @Override
    public Optional<User> getUser(UUID userId) {
        return Optional.empty();
    }

    @Override
    public int editUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(UUID userId) {
        return 0;
    }
}
