package com.example.prioritizationtool.model;


import javafx.util.Pair;

public class IssuePair {
    private Pair<Issue, Issue> pair;

    public IssuePair(Issue first, Issue second){
        pair = new Pair<>(first, second);
    }

    public Issue getFirst(){
        return pair.getKey();
    }

    public Issue getSecond(){
        return pair.getValue();
    }
}
