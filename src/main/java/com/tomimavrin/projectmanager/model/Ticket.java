package com.tomimavrin.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.UUID;

public class Ticket {

    private final UUID id;

    @NotBlank
    private final String title;
    private final String description;
    private final Timestamp dateCreated;
    private final UUID columnId;
    private final UUID createdBy;

    public Ticket(@JsonProperty("id") UUID id,
                  @JsonProperty("title") String title,
                  @JsonProperty("description") String description,
                  @JsonProperty("date_created") Timestamp dateCreated,
                  @JsonProperty("column_id") UUID columnId,
                  @JsonProperty("created_by")UUID createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.columnId = columnId;
        this.createdBy = createdBy;
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

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public UUID getColumnId() {
        return columnId;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }
}
