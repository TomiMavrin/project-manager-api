package com.tomimavrin.projectmanager.model;

import java.util.List;
import java.util.UUID;

public class Column {

    private final UUID id;
    private final String name;
    private final String color;
    private List<Ticket> tickets;
    private UUID board_id;

    public Column(UUID id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public UUID getBoard_id() {
        return board_id;
    }

    public void setBoard_id(UUID board_id) {
        this.board_id = board_id;
    }
}
