package com.example.accessingdatamysql.game;

import com.example.accessingdatamysql.minigame.Minigame;
import com.example.accessingdatamysql.minigame.MinigameService;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;
import com.example.accessingdatamysql.user.PlayerState;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.util.Lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

@WebMvcTest(controllers = GameController.class)
public class GameControllerTests {

    private static final Long TEST_GAME_ID = 1L;

    private static final Long TEST_PLAYER_ID = 1L;

    private static final Long TEST_MINIGAME_ID = 1L;

    @MockBean
    private GameService gameService;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private MinigameService minigameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Game game = new Game("partida1", State.UNSTARTED, new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), 1, 1);
        game.setId(TEST_GAME_ID);
        given(this.gameService.findAllGames()).willReturn(Lists.newArrayList(game));

        Player player = new Player("name1", "surname1", "password1", "email1", "nickname1", PlayerState.NO_PLAY);
        player.setId(TEST_PLAYER_ID);
        given(this.playerService.findAllPlayers()).willReturn(Lists.newArrayList(player));

        Minigame minigame = new Minigame("juego1", "descripcion juego 1");
        minigame.setId(TEST_MINIGAME_ID);
        given(this.minigameService.findAllMinigames()).willReturn(Lists.newArrayList(minigame));

        given(this.gameService.findGame(TEST_GAME_ID)).willReturn(Optional.of(game));
        given(this.playerService.findPlayer(TEST_PLAYER_ID)).willReturn(Optional.of(player));
        given(this.minigameService.findMinigame(TEST_MINIGAME_ID)).willReturn(Optional.of(minigame));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/games")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/games/{gameId}", TEST_GAME_ID)).andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Game game = new Game("partida2", State.UNSTARTED, new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), 1, 1);
        mockMvc.perform(
                post("/api/games").contentType("application/json").content(objectMapper.writeValueAsString(game)))
                .andExpect(status().isOk());
    }

    @Test
    void addPlayerToGame() throws Exception {
        mockMvc.perform(post("/api/games/{gameId}/players/{playerId}", TEST_GAME_ID, TEST_PLAYER_ID))
                .andExpect(status().isOk());
    }

    @Test
    void addMinigameToGame() throws Exception {
        mockMvc.perform(post("/api/games/{gameId}/minigames/{minigameId}", TEST_GAME_ID, TEST_MINIGAME_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/games/{gameId}", TEST_GAME_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/games")).andExpect(status().isOk());
    }

    @Test
    void testDeletePlayerFromGame() throws Exception {
        mockMvc.perform(delete("/api/games/{gameId}/players/{playerId}", TEST_GAME_ID, TEST_PLAYER_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteMinigameFromGame() throws Exception {
        mockMvc.perform(delete("/api/games/{gameId}/minigames/{minigameId}", TEST_GAME_ID, TEST_MINIGAME_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Game game = new Game();
        game.setName("partida3");
        mockMvc.perform(put("/api/games/{id}", TEST_GAME_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(game))).andExpect(status().isOk());
    }
}
