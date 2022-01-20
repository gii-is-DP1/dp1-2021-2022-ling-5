
package com.example.accessingdatamysql.ongoingRegaloEnvenenado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.card.CardService;
import com.example.accessingdatamysql.game.GameService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OnGoingRegaloEnvenenadoServiceTests {

    private static OnGoingRegaloEnvenenado game;
    @Autowired
    protected OnGoingRegaloEnvenenadoService onGoingRegaloEnvenenadoService;

    @Autowired
    protected CardService cardService;

    @Autowired
    protected GameService gameService;

    @BeforeEach
    void SetUp() {
        Long gameId = 2L;
        onGoingRegaloEnvenenadoService.createGame(gameId, gameService.findGame(gameId).get(),
                cardService.findAllCards());
        game = onGoingRegaloEnvenenadoService.getGame(2L);
    }

    @Test
    @Transactional(readOnly = true)
    void testGetById() {
        OnGoingRegaloEnvenenado game2 = onGoingRegaloEnvenenadoService.getGame(2L);
        assertEquals(game2.getPlayers(), game.getPlayers());
    }

    @Test
    @Transactional
    void testcreateGame() {
        Long gameId = 1L;
        onGoingRegaloEnvenenadoService.createGame(gameId, gameService.findGame(gameId).get(),
                cardService.findAllCards());
        Integer i = 0;
        for (OnGoingRegaloEnvenenado g : onGoingRegaloEnvenenadoService.getAll()) {
            i = i + 1;
        }
        assertEquals(2, i);
    }

    @Test
    @Transactional(readOnly = true)
    void testgetPoints() {
        Integer points = onGoingRegaloEnvenenadoService.getGame(2L).getPoints().get(2L);
        assertEquals(points, onGoingRegaloEnvenenadoService.getPoints(2L, 2L));
    }

    @Test
    @Transactional(readOnly = true)
    void testgetPlayerCard() {
        Card playerCard = onGoingRegaloEnvenenadoService.getGame(2L).getPlayerCard().get(2L);
        assertEquals(playerCard, onGoingRegaloEnvenenadoService.getPlayerCard(2L, 2L));
    }

    @Test
    @Transactional(readOnly = true)
    void testgetCenterCard() {
        Card centerCard = onGoingRegaloEnvenenadoService.getGame(2L).getCurrentCard();
        assertEquals(centerCard, onGoingRegaloEnvenenadoService.getCenterCard(2L));
    }

    @Test
    @Transactional
    void testnewCenterCard() {
        Card centerCard = onGoingRegaloEnvenenadoService.getGame(2L).getCurrentCard();
        RequestNewCard request = new RequestNewCard();
        request.setPlayerId(2L);
        onGoingRegaloEnvenenadoService.newCenterCard(2L, request);
        assertNotEquals(centerCard, onGoingRegaloEnvenenadoService.getCenterCard(2L));
    }

    @Test
    @Transactional
    void testAddPoints() {
        onGoingRegaloEnvenenadoService.addPoints(2L, 2L, 10);
        Integer points = onGoingRegaloEnvenenadoService.getGame(2L).getPoints().get(2L);
        assertEquals(10, points);
    }

    @Test
    @Transactional
    void testDeleteGame() {
        Integer size = 0;
        for (OnGoingRegaloEnvenenado g : onGoingRegaloEnvenenadoService.getAll()) {
            size = size + 1;
        }
        onGoingRegaloEnvenenadoService.deleteGame(2L);
        Integer i = 0;
        for (OnGoingRegaloEnvenenado g : onGoingRegaloEnvenenadoService.getAll()) {
            i = i + 1;
        }
        assertEquals(size - 1, i);
    }
}
