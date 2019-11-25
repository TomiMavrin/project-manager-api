package com.tomimavrin.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final String email;
    private final String password;

    @JsonCreator
    public User(@JsonProperty UUID id,
                @JsonProperty String name,
                @JsonProperty String email,
                @JsonProperty String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
