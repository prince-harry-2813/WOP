package com.plainid.assignment;

import com.plainid.assignment.dao.TrainerList;
import com.plainid.assignment.dao.primitives.Pokemon;
import com.plainid.assignment.dao.primitives.Trainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TrainerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * test to getAll method in trainer DAO
     */
    @Test
    public void testGetAllTrainers() {
        TrainerList trainerList = restTemplate.getForEntity("http://localhost:" + port + "/trainers", TrainerList.class).getBody();
        assertThat(trainerList).isNotNull();

    }

    /**
     * test for get trainer by name
     */
    @Test
    public void testGetTrainer() {
        String[] names = {"Arie", "Neo", "Trinity", "Smith", "Morpheus"};
        for (int i = 0; i < 5; i++) {
            Trainer trainer = restTemplate.getForObject("http://localhost:" + port + "/trainer/" + names[i], Trainer.class);
            assertThat(trainer).isNotNull();
            assertThat(trainer.getName()).isEqualTo(names[i]);
        }
    }

    @Test
    public void testGetTrainerInvalid() {
        Trainer trainer = restTemplate.getForObject("http://localhost:" + port + "/trainer/ss;%20DROP%20TABLE%20TRAINERS", Trainer.class);
        assertThat(trainer.getName()).isNull();
    }


    /**
     * test adding Pokemon to empty bag
     */
    @Test
    public void testUpdateTrainers() {
        String[] pNames = {"Bulbasaur", "Charizard", "Vulpix", "Golduck", "Victreebel", "Kingler", "Magmar", "Magikarp", "Lapras", "Vaporeon", "Chikorita", "Cyndaquil", "Sunflora", "Sharpedo", "Fennekin"};
        int index = new Random().nextInt(pNames.length - 1);
        Pokemon[] bag = restTemplate.getForObject("http://localhost:" + port + "/trainer/Morpheus/catch/" + pNames[index], Pokemon[].class);
        assertThat(bag).isNotNull();
        assertThat(bag[0].getName()).isEqualTo(pNames[index]);

    }
}
