package com.plainid.assignment.dao;

import com.plainid.assignment.converter.mapper.PokemonRawMapper;
import com.plainid.assignment.dao.primitives.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PokemonDAO implements IDAO<Pokemon, String> {

    private
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * get Pokemon by name
     * @param name of pokemon
     * @return Pokemon
     */
    @Override
    public Pokemon get(String name) {
        String sql = "SELECT * from POKEMON where NAME = ?";
        return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), new PokemonRawMapper()).get(0);
    }

    /**
     * get all pokemons
     * @return List of all pokemons
     */
    @Override
    public List<Pokemon> getAll() {
        String getAllQuery = "SELECT * from POKEMON ";
        return jdbcTemplate.query(getAllQuery, new PokemonRawMapper());
    }

    @Override
    public void save(Pokemon pokemon) {
    }

    @Override
    public void update(Pokemon... pokemon) {
    }

    @Override
    public void delete(Pokemon pokemon) {
    }
}