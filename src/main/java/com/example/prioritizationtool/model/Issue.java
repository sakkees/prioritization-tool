package com.example.prioritizationtool.model;

public class Issue {
    private String title;
    private String desc;

    public Issue(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
