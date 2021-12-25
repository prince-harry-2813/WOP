package com.plainid.assignment.controller;

import com.plainid.assignment.dao.primitives.Pokemon;
import com.plainid.assignment.dao.PokemonDAO;
import com.plainid.assignment.dao.primitives.Trainer;
import com.plainid.assignment.dao.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainerController {

    TrainerDAO tdao;
    PokemonDAO pdao;

    @Autowired
    public TrainerController(TrainerDAO tdao, PokemonDAO pdao) {
        this.tdao = tdao;
        this.pdao = pdao;
    }

    /**
     * Get all Trainers from server
     *
     * @return List &lt;Trainers&gt;
     */
    @GetMapping("/trainers")
    public List<Trainer> getTrainers() {
        return tdao.getAll();
    }


    @GetMapping("/trainer/{trainer_name}")
    public Trainer getTrainer(@PathVariable("trainer_name") String trainer_name) {
        return tdao.get(trainer_name);
    }


    @GetMapping("/trainer/{trainer_name}/catch/{pokemon_name}")
    public List<Pokemon> catchPokemon(@PathVariable("trainer_name") String trainer_name, @PathVariable("pokemon_name") String pokemon_name) {
        Trainer trainer = tdao.get(trainer_name);
        Pokemon pokemon = pdao.get(pokemon_name);
        trainer.addToBag(pokemon);
        tdao.updateBag(trainer);
        return trainer.getBag();
    }
}