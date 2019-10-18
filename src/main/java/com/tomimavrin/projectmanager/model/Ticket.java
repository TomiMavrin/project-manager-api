package com.tomimavrin.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

public class Ticket {

    private final UUID id;

    @NotBlank
    private final String title;
    private final String description;
    private final int priority;
    private final List<UUID> assignedUsers;

    public Ticket(@JsonProperty("id") UUID id,
                  @JsonProperty("title") String title,
                  @JsonProperty("description") String description,
                  @JsonProperty("priority") int priority,
                  @JsonProperty("assigned") List<UUID> assignedUsers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignedUsers = assignedUsers;
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

    public int getPriority() {
        return priority;
    }

    public List<UUID> getAssignedUsers() {
        return assignedUsers;
    }
}
