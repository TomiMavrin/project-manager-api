package com.tomimavrin.projectmanager.service;

import com.tomimavrin.projectmanager.dao.UserDao;
import com.tomimavrin.projectmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("users") UserDao userDao) {
        this.userDao = userDao;
    }

    public int createUser(User user){
        return this.userDao.createUser(user);
    }

    public Optional<User> getUser(UUID userId){
        return this.userDao.getUser(userId);
    };

    public Optional<User> getUser(String name){ return this.userDao.getUser(name); }

    public int editUser(User user){
        return this.userDao.editUser(user);
    };

    public int deleteUser(UUID userId){
        return this.userDao.deleteUser(userId);
    }
}
