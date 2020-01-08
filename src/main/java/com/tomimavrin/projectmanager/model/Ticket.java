package com.tomimavrin.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.UUID;

public class Ticket {

    private final UUID id;
    private final String title;
    private final String subtitle;
    private final String description;
    private final String color;
    private final Timestamp date_created;
    private final Timestamp date_due;
    private final UUID column_id;
    private final UUID created_by;
    private final UUID assigned_to;

    public Ticket(@JsonProperty("id") UUID id,
                  @JsonProperty("title") String title,
                  @JsonProperty("subtitle") String subtitle,
                  @JsonProperty("description") String description,
                  @JsonProperty("color") String color,
                  @JsonProperty("date_created") Timestamp date_created,
                  @JsonProperty("date_due") Timestamp date_due,
                  @JsonProperty("column_id") UUID column_id,
                  @JsonProperty("created_by") UUID created_by,
                  @JsonProperty("assigned_to") UUID assigned_to) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.color = color;
        this.date_created = date_created;
        this.date_due = date_due;
        this.column_id = column_id;
        this.created_by = created_by;
        this.assigned_to = assigned_to;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public UUID getColumn_id() {
        return column_id;
    }

    public UUID getCreated_by() {
        return created_by;
    }

    public String getColor() {
        return color;
    }

    public Timestamp getDate_due() {
        return date_due;
    }

    public UUID getAssigned_to() {
        return assigned_to;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
