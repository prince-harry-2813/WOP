package com.plainid.assignment.dao;

import com.plainid.assignment.dao.primitives.Pokemon;

import java.util.List;

/**
 * List of pokemons
 */
public class PokemonList {
    List<Pokemon> pokemons;

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}