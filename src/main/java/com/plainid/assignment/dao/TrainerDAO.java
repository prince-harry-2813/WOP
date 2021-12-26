package com.plainid.assignment.dao;

import com.plainid.assignment.converter.mapper.TrainerRowMapper;
import com.plainid.assignment.dao.primitives.Pokemon;
import com.plainid.assignment.dao.primitives.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TrainerDAO implements IDAO<Trainer, String> {

    private @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * get trainer from DB by name
     *
     * @param name of trainer to look up
     * @return Trainer
     */
    @Override
    public Trainer get(String name) {
        String sql = "SELECT TRAINER.TID, TRAINER.NAME, LEVEL,PID1, P1.NAME AS PNAME1, P1.TYPE AS TYPE1, PID2, P2.NAME AS PNAME2, P2.TYPE AS TYPE2,  PID3, P3.NAME AS PNAME3, P3.TYPE AS TYPE3 FROM TRAINER NATURAL JOIN BAG LEFT JOIN POKEMON P1 ON BAG.PID1  = P1.ID LEFT JOIN POKEMON P2 ON BAG.PID2 = P2.ID LEFT JOIN POKEMON P3 ON BAG.PID3 = P3.ID WHERE TRAINER.NAME = ?";

        return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setString(1, name), new TrainerRowMapper()).get(0);
    }

    /**
     * get all trainers from db
     *
     * @return list of all trainers
     */
    @Override
    public List<Trainer> getAll() {
        String sql = "SELECT TRAINER.TID, TRAINER.NAME, LEVEL,PID1, P1.NAME AS PNAME1, P1.TYPE AS TYPE1, PID2, P2.NAME AS PNAME2, P2.TYPE AS TYPE2,  PID3, P3.NAME AS PNAME3, P3.TYPE AS TYPE3 FROM TRAINER NATURAL JOIN BAG LEFT JOIN POKEMON P1 ON BAG.PID1  = P1.ID LEFT JOIN POKEMON P2 ON BAG.PID2 = P2.ID LEFT JOIN POKEMON P3 ON BAG.PID3 = P3.ID ORDER BY LEVEL DESC";

        return jdbcTemplate.query(sql, new TrainerRowMapper());
    }

    /**
     * save new trainer to DB
     *
     * @param trainer new trainer to add
     */
    @Override
    public void save(Trainer trainer) {
        jdbcTemplate.update("insert into TRAINER (NAME, LEVEL, TID) VALUES (" + trainer.getName() + ',' + trainer.getLevel() + ',' + trainer.getId() + "); INSERT INTO BAG (TID) VALUES (" + trainer.getId() + ");");
    }

    /**
     * updating Trainer level
     *
     * @param trainers trainers to update
     */
    @Override
    public void update(Trainer... trainers) {
        for (Trainer t : trainers) {
            String sql = "update TRAINER set TRAINER.LEVEL =" + t.getLevel() + " Where TID = " + t.getId();
            jdbcTemplate.update(sql);
        }
    }


    /**
     * update trainer bag
     *
     * @param trainer to update his bag
     */
    public void updateBag(Trainer trainer) {
        int i = 1;
        for (Pokemon po : trainer.getBag()) {
            String updateQuery = " UPDATE BAG SET PID" + i + " = " + po.getId() + " WHERE TID = " + trainer.getId();
            jdbcTemplate.update(updateQuery);
            i++;
        }
    }

    /**
     * delete trainer from db
     *
     * @param trainer to delete
     */
    @Override
    public void delete(Trainer trainer) {
        jdbcTemplate.update("DELETE from BAG where TID = " + trainer.getId() + "; DELETE FROM TRAINER WHERE TID =" + trainer.getId());
    }
}
