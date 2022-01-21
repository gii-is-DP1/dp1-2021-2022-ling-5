package com.example.accessingdatamysql.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameServiceTests {
    @Autowired
    protected GameService gameService;

    @Test
    @Transactional
    public void shouldInsertGame() {
        List<Game> games = this.gameService.findAllGames();
        int found = games.size();

        Game game = new Game();
        game.setCreator(1L);
        game.setName("Partida1");
        this.gameService.saveGame(game);
        assertNotEquals(game.getId(), 0L);

        games = this.gameService.findAllGames();
        assertEquals(games.size(), found + 1);
    }

    @Test
    @Transactional(readOnly = true)
    public void shouldFindSingleGame() {
        Optional<Game> gameOpt = this.gameService.findGame(1L);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            assertEquals(game.getName(), "juegoUno");
        }
    }

    @Test
    @Transactional(readOnly = true)
    public void shouldFindSingleGameByName() {
        Optional<Game> gameOpt = this.gameService.findGameByName("juegoUno");
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            assertEquals(game.getName(), "juegoUno");
        }
    }

    @Test
    @Transactional
    void shouldUpdateGame() {
        Optional<Game> game = this.gameService.findGame(1L);
        if (game.isPresent()) {
            String oldName = game.get().getName();
            String newName = oldName + "X";

            game.get().setName(newName);
            this.gameService.saveGame(game.get());

            game = this.gameService.findGame(1L);
            if (game.isPresent()) {
                assertEquals(game.get().getName(), newName);
            }
        }
    }

    @Test
    @Transactional
    void shouldDeleteGame() {
        Game game = new Game();
        game.setName("partida2");
        game = this.gameService.saveGame(game);
        List<Game> games = this.gameService.findAllGames();
        int found = games.size();

        this.gameService.deleteGame(game.getId());
        games = this.gameService.findAllGames();
        assertEquals(games.size(), found - 1);

    }

}
