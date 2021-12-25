package com.plainid.assignment.converter.mapper;

import com.plainid.assignment.dao.primitives.Pokemon;
import com.plainid.assignment.dao.PokemonType;
import com.plainid.assignment.dao.primitives.Trainer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class to handle SQL trainer results
 */
public class TrainerSetExtractor implements ResultSetExtractor <List<Trainer>> {

    @Override
    public List<Trainer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Trainer> lt = new ArrayList<>();
        while (resultSet.next()) {
            Trainer trainer = new Trainer();
            trainer.setId(resultSet.getInt("TID"));
            trainer.setName(resultSet.getString("NAME"));
            trainer.setLevel(resultSet.getInt("LEVEL"));
            for (int i = 1; i < 4; i++) {
                if (resultSet.getInt("PID" + i) != 0) {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setId(resultSet.getInt("PID" + i));
                    pokemon.setName(resultSet.getString("PNAME" + i));
                    pokemon.setType(Enum.valueOf(PokemonType.class, resultSet.getString("TYPE" + i)));
                    trainer.addToBag(pokemon);
                }

            }
            lt.add(trainer);
        }
        return lt;
    }

}