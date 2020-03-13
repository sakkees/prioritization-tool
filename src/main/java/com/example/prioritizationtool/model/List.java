package com.example.prioritizationtool.model;

import java.util.ArrayList;

public abstract class List<E>{
     ArrayList<E> list;
     String owner;

    public List(String owner){
        this.list = new ArrayList<E>();
        this.owner = owner;
    }

    public void add(E e){
        list.add(e);
    }

    public void remove(E e){
        list.remove(e);
    }

    abstract public E get(String id);

    public String getOwner(){
        return this.owner;
    }

    public boolean contains(E e){
        return list.contains(e);
    }
}
