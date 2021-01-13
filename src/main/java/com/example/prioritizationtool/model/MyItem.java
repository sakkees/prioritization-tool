package com.example.prioritizationtool.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class MyItem{
    @Id
    private final String id = UUID.randomUUID().toString();

    private String title;
    private String description;

    public MyItem(){
    }

    public MyItem(String title, String description){
        this.title = title;
        this.description = description;
    }

    public boolean equals(MyItem issue){
        if(issue.getClass().equals(MyItem.class)){
            if((issue.getDescription().equals(this.getDescription()) && issue.getTitle().equals(this.getTitle()))){
                return true;
            }
        }
        return false;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }
}
