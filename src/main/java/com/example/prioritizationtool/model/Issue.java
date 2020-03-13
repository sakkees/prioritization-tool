package com.example.prioritizationtool.model;

public class Issue extends Item {
    private String title;
    private String description;

    public Issue(){
    }

    public Issue(String title, String description){
        this.title = title;
        this.description = description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
