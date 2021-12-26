package com.plainid.assignment.dao;

/**
 * In our pokemon world there are 3 types of pokemons:
 * Grass, Water and fire.
 * Pokemon from particular type has advantage or weakness against other types
 */
public enum PokemonType {
    Grass(0),
    Fire(1),
    Water(2);

    private final int value;

    PokemonType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
