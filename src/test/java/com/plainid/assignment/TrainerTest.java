package com.plainid.assignment;

import com.plainid.assignment.dao.TrainerList;
import com.plainid.assignment.dao.primitives.Pokemon;
import com.plainid.assignment.dao.primitives.Trainer;
import org.assertj.core.api.Assertions;
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
        assertThat(trainerList.getTrainers().size()).isEqualTo(5);
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

    /**
     * test for get trainer by name with invalid input
     */
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

    /**
     * test adding Pokemon to a full bag - include Bonus Check
     * initial bag status:[1 : Bulbasaur , 2 : Lapras, 3 : Fennekin]
     * after catch we expect to receive updated bag: [1: Lapras , 2: Fennekin 3: NewOther]
     */
    @Test
    public void testUpdateTrainerWithFullBag() {
        String[] pNames = {"Charizard", "Vulpix", "Golduck", "Victreebel", "Kingler", "Magmar", "Magikarp", "Vaporeon", "Chikorita", "Cyndaquil", "Sunflora", "Sharpedo"};
        int index = new Random().nextInt(pNames.length - 1);
        Pokemon[] bag = restTemplate.getForObject("http://localhost:" + port + "/trainer/Arie/catch/" + pNames[index], Pokemon[].class);
        assertThat(bag[0].getName()).isEqualTo("Lapras");
        assertThat(bag[1].getName()).isEqualTo("Fennekin");
        assertThat(bag[2].getName()).isEqualTo(pNames[index]);
        Assertions.assertThatExceptionOfType(ArrayIndexOutOfBoundsException.class).isThrownBy(() -> bag[3].getName());
    }

    /**
     * test adding Pokemon to a bag that already contain that Pokemon
     * initial bag status: [1 : Sharpedo , 2 : Vaporeon, 3 : ]
     * after catch we expect to receive same bag: [1 : Sharpedo , 2 : Vaporeon, 3 : ]
     */
    @Test
    public void testUpdateTrainerWithExistPokemon() {
        Pokemon[] bag = restTemplate.getForObject("http://localhost:" + port + "/trainer/Smith/catch/Sharpedo", Pokemon[].class);
        assertThat(bag[0].getName()).isEqualTo("Sharpedo");
        assertThat(bag[1].getName()).isEqualTo("Vaporeon");
        Assertions.assertThatExceptionOfType(ArrayIndexOutOfBoundsException.class).isThrownBy(() -> bag[2].getName());
    }
}
