package com.example.accessingdatamysql.result;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameService;
import com.example.accessingdatamysql.game.State;
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

@WebMvcTest(controllers = ResultController.class)
public class ResultControllerTests {

    private static final Long TEST_RESULT_ID = 1L;

    private static final Long TEST_PLAYER_ID = 1L;

    private static final Long TEST_GAME_ID = 1L;

    @MockBean
    private ResultService resultService;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Result result = new Result("Minigame1:5, Minigame2:5, Minigame3:5", 15);
        result.setId(TEST_RESULT_ID);
        given(this.resultService.findAllResults()).willReturn(Lists.newArrayList(result));

        Player player = new Player("name1", "surname1", "password1", "email1", "nickname1", PlayerState.NO_PLAY);
        player.setId(TEST_PLAYER_ID);
        given(this.playerService.findAllPlayers()).willReturn(Lists.newArrayList(player));

        Game game = new Game("partida1", State.UNSTARTED, new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), 1, 1);
        game.setId(TEST_GAME_ID);
        given(this.gameService.findAllGames()).willReturn(Lists.newArrayList(game));

        given(this.resultService.findResult(TEST_RESULT_ID)).willReturn(Optional.of(result));
        given(this.playerService.findPlayer(TEST_PLAYER_ID)).willReturn(Optional.of(player));
        given(this.gameService.findGame(TEST_GAME_ID)).willReturn(Optional.of(game));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/results")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/results/{resultId}", TEST_RESULT_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByGame() throws Exception {
        mockMvc.perform(get("/api/games/{gameId}/results", TEST_GAME_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByPlayer() throws Exception {
        mockMvc.perform(get("/api/players/{playerId}/results", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testCreationResult() throws Exception {
        Result result = new Result("Minigame1:5, Minigame2:10, Minigame3:5", 20);
        mockMvc.perform(post("/api/games/{gameId}/players/{playerId}/results", TEST_GAME_ID, TEST_PLAYER_ID)
                .contentType("application/json").content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/results/{resultId}", TEST_RESULT_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/results")).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllByGame() throws Exception {
        mockMvc.perform(delete("/api/games/{gameId}/results", TEST_GAME_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllByPlayer() throws Exception {
        mockMvc.perform(delete("/api/players/{playerId}/results", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Result result = new Result();
        result.setTotalPoints(30);
        mockMvc.perform(put("/api/results/{id}", TEST_RESULT_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(result))).andExpect(status().isOk());
    }
}
