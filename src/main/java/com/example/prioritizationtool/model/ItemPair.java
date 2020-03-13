package com.example.prioritizationtool.model;

import javafx.util.Pair;

public class ItemPair extends Pair<Item, Item> {
    public ItemPair(Item key, Item value) {
        super(key, value);
    }

    public Item getFirst() {
        return super.getKey();
    }

    public Item getSecond() {
        return super.getValue();
    }

}
