package com.tomimavrin.projectmanager.model;

import java.util.List;
import java.util.UUID;

public class Board {

    private final String name;
    private final String description;

    public Board(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
