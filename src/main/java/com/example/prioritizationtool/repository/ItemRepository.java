package com.example.prioritizationtool.repository;

import com.example.prioritizationtool.model.Issue;
import com.example.prioritizationtool.model.Item;

import java.util.ArrayList;

public interface ItemRepository<Item> {
    ArrayList<Item> findAll();
    Item findByTitle(String id);
    void put(Item issue);
    void delete(Item issue);
}
