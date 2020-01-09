package com.tomimavrin.projectmanager.model;

import java.util.List;
import java.util.UUID;

public class Board {

    private final UUID id;
    private final String name;
    private final UUID owner_id;
    private final String description;
    private List<Column> columnList;

    public Board(UUID id, String name, UUID owner_id, String description) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
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

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public UUID getOwner_id() {
        return owner_id;
    }
}
