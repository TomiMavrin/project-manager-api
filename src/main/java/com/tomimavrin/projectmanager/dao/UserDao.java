package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.Ticket;
import com.tomimavrin.projectmanager.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    int createUser(UUID id, User user);

    default int createUser(User user){
        UUID id = UUID.randomUUID();
        return createUser(id, user);
    };

    Optional<Ticket> getUser(UUID userId);

    int editUser(UUID userId);

    int deleteUser(UUID userId);
}
