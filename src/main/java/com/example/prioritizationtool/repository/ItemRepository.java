package com.example.prioritizationtool.repository;

import com.example.prioritizationtool.model.MyItem;
import com.example.prioritizationtool.model.MyList;

public interface ItemRepository{
    MyList getListById(String itemId);
    MyItem getItemById(String itemId, String listId);
    void deleteItemById(String itemId, String listId);
    void put(MyList list);
    void put(MyItem item, String listId);
    void deleteListById(String listId);
}
