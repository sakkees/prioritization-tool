package com.example.prioritizationtool.model;

import javafx.util.Pair;

public class ItemPair extends Pair<MyItem, MyItem> {
    public ItemPair(MyItem key, MyItem value) {
        super(key, value);
    }

    public MyItem getFirst() {
        return super.getKey();
    }

    public MyItem getSecond() {
        return super.getValue();
    }

}
