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

    @Test
    public void testBattle() {
        Battle battle = restTemplate.getForObject("http://localhost:" + port + "/battle/Arie/Arie", Battle.class);
        assertThat(battle.getStatus() == "Error");
        assertThat(battle.getStatus() == "canceled");
    }

}
