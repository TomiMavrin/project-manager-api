package com.tomimavrin.projectmanager.dao;

import com.tomimavrin.projectmanager.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    int createUser(User user) throws Exception;

    Optional<User> getUser(UUID userId);

    Optional<User> getUser(String name);

    int editUser(User user);

    int deleteUser(UUID userId);
}
