package com.plainid.assignment.controller;


import com.plainid.assignment.service.Battle;
import com.plainid.assignment.dao.primitives.Trainer;
import com.plainid.assignment.dao.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battle")
public class BattleController {

    TrainerDAO tDao;

    @Autowired
    public BattleController(TrainerDAO tDao) {
        this.tDao = tDao;
    }

    @GetMapping("/{trainer_1}/{trainer_2}")
    public Battle fight(@PathVariable("trainer_1") String trainer_1, @PathVariable("trainer_2") String trainer_2) {

        Trainer t1 = tDao.get(trainer_1);
        Trainer t2 = tDao.get(trainer_2);
        Battle response = new Battle().calculateFight(t1, t2);
        tDao.update(t1,t2);
        return response;
    }
}
