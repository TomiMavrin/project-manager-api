package com.tomimavrin.projectmanager.model;

import java.util.List;
import java.util.UUID;

public class Board {

    private final UUID id;
    private final List<Column> columns;
    private final String name;

    public Board(UUID id, List<Column> columns, String name) {
        this.id = id;
        this.columns = columns;
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
