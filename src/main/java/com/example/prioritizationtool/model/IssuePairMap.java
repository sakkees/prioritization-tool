package com.example.prioritizationtool.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IssuePairMap {
    private Map<IssuePair, ArrayList<Double>> map;

    public IssuePairMap(String owner){
         map = new HashMap<>();
    }
    public void put(IssuePair pair, Double value){
        Collection<Double> valuesCollection = map.get(pair);
        valuesCollection.add(value);
        ArrayList<Double> valuesList = new ArrayList<>(valuesCollection);
        map.put(pair, valuesList);
    }

    public void remove(IssuePair pair){
        map.remove(pair);
    }

    public ArrayList<Double> getValue(IssuePair pair){
        return map.get(pair);
    }
}
