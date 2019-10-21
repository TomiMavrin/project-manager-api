package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    int createUser(UUID id, User user);

    default int createUser(User user){
        UUID id = UUID.randomUUID();
        return createUser(id, user);
    };

    Optional<User> getUser(UUID userId);

    int editUser(User user);

    int deleteUser(UUID userId);
}
