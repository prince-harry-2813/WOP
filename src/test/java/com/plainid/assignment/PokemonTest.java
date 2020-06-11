package com.plainid.assignment;

import com.plainid.assignment.dao.PokemonList;
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
public class PokemonTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllPokemons(){
        PokemonList pokemonList = restTemplate.getForEntity("http://localhost:" + port + "/pokemon/list",
                PokemonList.class).getBody();
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.getPokemons()).isNotNull();

    }
}
