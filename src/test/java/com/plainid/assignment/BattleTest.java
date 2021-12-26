package com.plainid.assignment;

import com.plainid.assignment.service.Battle;
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
public class BattleTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * test battle with invalid params:
     * trainer 1 & 2 are the same
     */
    @Test
    public void testBattle() {
        Battle battle = restTemplate.getForObject("http://localhost:" + port + "/battle/Arie/Arie", Battle.class);
        assertThat(battle.getStatus()).isEqualTo("Error");
        assertThat(battle.getMessage()).isEqualTo("canceled");

    }

    /**
     * valid params of trainers names
     * trainer 2 without a full bag
     */
    @Test
    public void testBattle2() {
        Battle battle = restTemplate.getForObject("http://localhost:" + port + "/battle/Arie/Morpheus", Battle.class);
        assertThat(battle.getStatus()).isEqualTo("Error");
        assertThat(battle.getMessage()).isEqualTo("canceled");
    }

    /**
     * test battle where the expected winner is trainer 1
     */
    @Test
    public void testBattleThatT1Wins() {
        Battle battle = restTemplate.getForObject("http://localhost:" + port + "/battle/Neo/Arie", Battle.class);
        assertThat(battle.getStatus()).isEqualTo("Success");
        assertThat(battle.getMessage()).isEqualTo("Neo wins");
    }

    /**
     * test a draw battle
     */
    @Test
    public void testDrawBattle() {
        Battle battle = restTemplate.getForObject("http://localhost:" + port + "/battle/Arie/Trinity", Battle.class);
        assertThat(battle.getStatus()).isEqualTo("Draw");
        assertThat(battle.getMessage()).isEqualTo("draw");
    }
}
