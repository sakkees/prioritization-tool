package com.example.prioritizationtool.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public abstract class Item {
    @Id
    private final String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
    public abstract String getTitle();
    public abstract String getDescription();
    public abstract void setDescription(String description);
}
