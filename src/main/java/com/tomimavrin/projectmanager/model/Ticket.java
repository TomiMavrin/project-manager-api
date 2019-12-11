package com.tomimavrin.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.UUID;

public class Ticket {

    private final UUID id;
    private final String title;
    private final String description;
    private final Timestamp date_created;
    private final UUID column_id;
    private final UUID created_by;

    public Ticket(@JsonProperty("id") UUID id,
                  @JsonProperty("title") String title,
                  @JsonProperty("description") String description,
                  @JsonProperty("date_created") Timestamp date_created,
                  @JsonProperty("column_id") UUID column_id,
                  @JsonProperty("created_by")UUID created_by) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_created = date_created;
        this.column_id = column_id;
        this.created_by = created_by;
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
}
