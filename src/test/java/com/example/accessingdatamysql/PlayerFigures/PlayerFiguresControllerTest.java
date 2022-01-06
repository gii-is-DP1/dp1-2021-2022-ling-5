package com.example.accessingdatamysql.PlayerFigures;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.playerfigures.PlayerFigures;
import com.example.accessingdatamysql.playerfigures.PlayerFiguresController;
import com.example.accessingdatamysql.playerfigures.PlayerFiguresService;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;
import com.example.accessingdatamysql.user.PlayerState;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@WebMvcTest(controllers = PlayerFiguresController.class)
public class PlayerFiguresControllerTest {

    private static final Long TEST_PF_ID = 1L;

    private static final Long TEST_PLAYER_ID = 1L;

    private static final Long TEST_FIGURE_ID = 1L;
    
    @MockBean
    private PlayerFiguresService playerFiguresService;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private FigureService figureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        PlayerFigures pf = new PlayerFigures(10);
        pf.setId(TEST_PF_ID);
        given(this.playerFiguresService.findAllPlayerFigures()).willReturn(Lists.newArrayList(pf));

        Player player = new Player("name1", "surname1", "password1", "email1", "nickname1", PlayerState.NO_PLAY);
        player.setId(TEST_PLAYER_ID);
        given(this.playerService.findAllPlayers()).willReturn(Lists.newArrayList(player));

        Figure figure = new Figure("trial");
        figure.setId(TEST_FIGURE_ID);
        given(this.figureService.findAllFigures()).willReturn(Lists.newArrayList(figure));

        given(this.playerFiguresService.findPlayerFigures(TEST_PF_ID)).willReturn(Optional.of(pf));
        given(this.playerService.findPlayer(TEST_PLAYER_ID)).willReturn(Optional.of(player));
        given(this.figureService.findFigure(TEST_FIGURE_ID)).willReturn(Optional.of(figure));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/playerfigures")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/playerfigures/{id}", TEST_PF_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByfigure() throws Exception {
        mockMvc.perform(get("/api/figures/{figureId}/playerfigures", TEST_FIGURE_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByPlayer() throws Exception {
        mockMvc.perform(get("/api/players/{playerId}/playerfigures", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testCreationResult() throws Exception {
        PlayerFigures pf = new PlayerFigures(15);
        mockMvc.perform(post("/api/figure/{figureId}/players/{playerId}/playerfigures", TEST_FIGURE_ID, TEST_PLAYER_ID)
                .contentType("application/json").content(objectMapper.writeValueAsString(pf)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/playerfigures/{id}", TEST_PF_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllByFigure() throws Exception {
        mockMvc.perform(delete("/api/figure/{figureId}/playerfigures", TEST_FIGURE_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllByPlayer() throws Exception {
        mockMvc.perform(delete("/api/players/{playerId}/playerfigures", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

}
