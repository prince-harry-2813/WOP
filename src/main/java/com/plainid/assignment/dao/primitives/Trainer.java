package com.plainid.assignment.dao.primitives;

import com.plainid.assignment.dao.primitives.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private int id;
    private String name;
    private int level;
    private List<Pokemon> bag = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Pokemon> getBag() {
        return bag;
    }

    public void setBag(List<Pokemon> bag) {
        this.bag = bag;
    }

    public void addToBag(Pokemon pokemon) {
        this.bag.add(pokemon);
        if (bag.size() > 3)
            bag.remove(0);
    }

}
