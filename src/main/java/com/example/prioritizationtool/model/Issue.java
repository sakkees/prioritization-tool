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
    public boolean equals(Object issue){
        if(issue.getClass().equals(Issue.class)){
            if(((Issue) issue).getDescription().equals(this.getDescription()) && ((Issue) issue).getTitle().equals(this.getTitle())){
                return true;
            }
        }
        return false;
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
