package com.plainid.assignment.dao;

/**
 * Pokémon are creatures of all shapes and sizes who live in the wild or alongside humans.
 * For the most part, Pokémon do not speak except to utter their names.
 * There are currently more than 700 creatures that inhabit the Pokémon universe.
 */
public class Pokemon {

    int id;
    String name;
    PokemonType type;

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

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
}
