package com.tomimavrin.projectmanager.model;

import java.util.List;
import java.util.UUID;

public class Column {

    private final UUID id;
    private final String name;
    private final String category;
    private final List<Ticket> tickets;

    public Column(UUID id, String name, String color, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.category = color;
        this.tickets = tickets;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
