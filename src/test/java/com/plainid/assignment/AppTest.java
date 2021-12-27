package com.plainid.assignment;

import com.plainid.assignment.dao.TrainerList;
import com.plainid.assignment.dao.primitives.Pokemon;
import com.plainid.assignment.dao.primitives.Trainer;
import com.plainid.assignment.service.Battle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AppTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * System testing to check if the app meet the requirements.
     */
    @Test
    public void testAllSystem() {

        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
        /**
         * add pokemon to Smith bag and check if its added
         */
        Pokemon[] smithBag = restTemplate.getForObject("/trainer/Smith/catch/Sunflora", Pokemon[].class);
        Trainer smith1 = restTemplate.getForObject("/trainer/Smith", Trainer.class);

        assertThat(smith1.getBag().get(2).getName()).isEqualTo("Sunflora");

        /**
         * check battle operation and results
         */
        Battle battle1 = restTemplate.getForObject("/battle/Trinity/Smith", Battle.class);
        Trainer smith2 = restTemplate.getForObject("/trainer/Smith", Trainer.class);

        assertThat(battle1.getMessage()).isEqualTo("Smith wins");
        //smith has 2 XP
        assertThat(smith2.getLevel()).isEqualTo(2);

        /**
         * check order of Trainer list
         */
        restTemplate.getForObject("/battle/Neo/Arie", Void.class); //Neo wins and has 2 Xp
        restTemplate.getForObject("/battle/Neo/Arie", Void.class);// Neo wins and has 4 Xp
        restTemplate.getForObject("/battle/Trinity/Arie", Void.class);//  Draw Trinity & Arie has 1 Xp
        restTemplate.getForObject("/battle/Trinity/Neo", Void.class);//  Draw Trinity & neo has + 1 Xp

        TrainerList trainerList = restTemplate.getForEntity("/trainers", TrainerList.class).getBody();
        assertThat(trainerList.getTrainers().get(0).getName()).isEqualTo("Neo");
        assertThat(trainerList.getTrainers().get(3).getName()).isEqualTo("Arie");
        assertThat(trainerList.getTrainers().get(4).getName()).isEqualTo("Morpheus");

    }


}
