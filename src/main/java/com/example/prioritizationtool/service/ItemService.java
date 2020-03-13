package com.example.prioritizationtool.service;

import java.util.ArrayList;

public interface ItemService<Item> {
    ArrayList<Item> findAll();
    void add(Item Item);
    void create(String title, String description);
    void updateDescription(Item Item, String description);
    void delete(Item Item);
    Item getByTitle(String title);
}
