package com.example.prioritizationtool.model;

import org.springframework.data.annotation.Id;

public class Issue {
    @Id
    private String title;
    private String description;

    public Issue(){
    }

    public Issue(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
