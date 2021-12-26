package com.plainid.assignment.dao;

import com.plainid.assignment.dao.primitives.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * List of pojenons
 */
public class PokemonList {
    List<Pokemon> pokemons = new ArrayList<>();

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }


}