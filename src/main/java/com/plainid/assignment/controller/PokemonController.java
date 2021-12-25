package com.plainid.assignment.controller;


import com.plainid.assignment.converter.mapper.PokemonRawMapper;
import com.plainid.assignment.dao.primitives.Pokemon;
import com.plainid.assignment.dao.PokemonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Pokemon controller
 */
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get all pokemons in the world
     * @return List of pokemons in the world.
     */
    @GetMapping("/list")
    public PokemonList getPokemons() {
        List<Pokemon> rows = jdbcTemplate.query("SELECT * from POKEMON",new PokemonRawMapper());
        PokemonList pokemonList = new PokemonList();
        pokemonList.setPokemons(rows);
        return pokemonList;
    }
}
