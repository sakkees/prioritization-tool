package com.example.prioritizationtool.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PairMap {
    private Map<ItemPair, ArrayList<Double>> map;

    public PairMap(String owner){
         map = new HashMap<>();
    }
    public void put(ItemPair pair, Double value){
        Collection<Double> valuesCollection = map.get(pair);
        valuesCollection.add(value);
        ArrayList<Double> valuesList = new ArrayList<>(valuesCollection);
        map.put(pair, valuesList);
    }

    public void remove(ItemPair pair){
        map.remove(pair);
    }

    public ArrayList<Double> getValue(ItemPair pair){
        return map.get(pair);
    }
}
