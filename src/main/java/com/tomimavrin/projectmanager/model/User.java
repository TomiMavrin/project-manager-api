package com.tomimavrin.projectmanager.model;

import java.util.List;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final String email;
    private final List<UUID> boards;

    public User(UUID id, String name, String email, List<UUID> boards) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.boards = boards;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<UUID> getBoards() {
        return boards;
    }
}
