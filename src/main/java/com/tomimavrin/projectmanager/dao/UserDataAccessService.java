package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public int createUser(UUID id, User user, String password) {
        final String sql = "INSERT INTO USERS (id, email, password, name, enabled) VALUES(?, ?, ?, ?, ?)";
        final String auth = "INSERT INTO AUTHORITIES (email, authority) VALUES (?, ?)";
        String code = passEncoder().encode(password);
        jdbcTemplate.update(auth,  user.getEmail(), "USER");
        return jdbcTemplate.update(sql , id, user.getEmail(), code, user.getName(), true);
    }

    @Override
    public Optional<User> getUser(UUID userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String name) {

        final String query = "SELECT * FROM USERS WHERE email='"+ name +"' ORDER BY email FETCH FIRST ROW ONLY;";
        List<User> users = jdbcTemplate.query(query,  (resultSet, i) ->{
            UUID uuid = UUID.fromString(resultSet.getString("id"));
            String email = resultSet.getString("email");
            String username = resultSet.getString("name");
            return new User(uuid,username,email);
        });
        if(users.isEmpty()){
            return Optional.empty();
        }
        else {
            return Optional.of(users.get(0));
        }
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
