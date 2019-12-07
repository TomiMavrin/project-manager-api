package com.tomimavrin.projectmanager.model;

import java.util.List;
import java.util.UUID;

public class Board {

    private final UUID id;
    private final String name;
    private final String description;

    public Board(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getId() {
        return id;
    }
}
