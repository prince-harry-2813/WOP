package com.plainid.assignment;

import com.plainid.assignment.dao.primitives.Trainer;
import com.plainid.assignment.dao.TrainerList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

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
        TrainerList trainerList = restTemplate.getForEntity("http://localhost:" + port + "/trainers",
                TrainerList.class).getBody();

        assertThat(trainerList).isNotNull();
        assertThat(trainerList.getTrainers()).isNotNull();

    }

    @Test
    public void testGetTrainer() {
        String[] names = {"Arie", "Neo", "Trinity", "Smith", "Morpheus"};
        for (int i = 0; i < 5; i++) {
            Trainer trainer = restTemplate.getForObject("http://localhost:" + port + "/trainer/" + names[i], Trainer.class);
            assertThat(trainer).isNotNull();
            assertThat(trainer.getName() == names[i]);
        }
    }

    @Test
    public void testUpdateTrainer(){

    }
}
