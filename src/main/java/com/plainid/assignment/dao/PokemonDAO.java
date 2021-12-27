package com.plainid.assignment.dao;

import com.plainid.assignment.converter.mapper.PokemonRawMapper;
import com.plainid.assignment.dao.primitives.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * implementation of DAO for Pokemons
 */
@Repository
public class PokemonDAO implements IDAO<Pokemon, String> {

    private @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * get Pokemon by name,
     *
     * @param name of pokemon
     * @return Pokemon that it's name match to the param
     */
    @Override
    public Pokemon get(String name) {
        String sql = "SELECT * from POKEMON where NAME = ?";
        return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), new PokemonRawMapper()).get(0);
    }

    /**
     * get all pokemons
     *
     * @return List of all pokemons
     */
    @Override
    public List<Pokemon> getAll() {
        String getAllQuery = "SELECT * from POKEMON ";
        return jdbcTemplate.query(getAllQuery, new PokemonRawMapper());
    }

    /**
     * Add new pokemon to db
     * @param pokemon to add
     */
    @Override
    public void save(Pokemon pokemon) {
        jdbcTemplate.update("INSERT into POKEMON (ID,NAME,TYPE) VALUES (" + pokemon.getId() + ',' + pokemon.getName() + ',' + pokemon.getType().name() + ");");
    }

    /**
     * update pokemon details in DB
     *
     * @param pokemons
     */
    @Override
    public void update(Pokemon... pokemons) {
        for (Pokemon pok : pokemons) {
            jdbcTemplate.update("update POKEMON set NAME =" + pok.getName() + ",TYPE = " + pok.getType().name() + " WHERE ID = " + pok.getId());
        }
    }

    /**
     * delete Pokemon from DB
     *
     * @param pokemon
     */
    @Override
    public void delete(Pokemon pokemon) {
        for (int i = 1; i < 4; i++)
            jdbcTemplate.update(" UPDATE BAG SET PID" + i + " = NULL WHERE PID" + i + " = " + pokemon.getId());
        jdbcTemplate.update("DELETE  FROM POKEMON WHERE ID = " + pokemon.getId());
    }
}
