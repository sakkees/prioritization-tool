package com.example.prioritizationtool.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

public class MyList{
    @Id
    private final String id = UUID.randomUUID().toString();

    private ArrayList<MyItem> list;
    private String owner;

    public MyList(){
    }

    public MyList(String owner){
        this.list = new ArrayList<MyItem>();
        this.owner = owner;
    }

    public void add(MyItem item){
        list.add(item);
    }

    public void remove(MyItem item){
        list.remove(item);
    }

    public String getOwner(){
        return this.owner;
    }

    public boolean contains(MyItem item){
        return list.contains(item);
    }
    public String getId(){
        return id;
    }
    public ArrayList<MyItem> getList(){
        return list;
    }

    public MyItem getItemById(String id) {
        for (MyItem issue : list) {
            if (issue.getId().equals(id)) {
                return issue;
            }
        }
        return null;
    }
}
