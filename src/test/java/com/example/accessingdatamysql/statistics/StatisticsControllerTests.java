package com.example.accessingdatamysql.statistics;

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
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import javax.transaction.Transactional;

@WebMvcTest(controllers = StatisticsController.class)

public class StatisticsControllerTests {

    private static final Long TEST_PLAYER_ID = 1L;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private PlayerService playerService;



    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPointsByMinigames() throws Exception {

        mockMvc.perform(get("/api/statistics/pointsbyminigames/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testgetMaxMinFigures() throws Exception {

        mockMvc.perform(get("/api/statistics/maxminfigure/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());

    
    }
    
    @Test
    void testRanking10() throws Exception {

        mockMvc.perform(get("/api/statistics/maxminfigure/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());


    }
    
    @Test
    void testRanking() throws Exception {

        mockMvc.perform(get("/api/statistics/ranking/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }
    
    @Test
    void testPlayerPerGame() throws Exception {

        mockMvc.perform(get("/api/statistics/playerpergame/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }
    
    @Test
    void testPropTotal() throws Exception {

        mockMvc.perform(get("/api/statistics/propTotal/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }
    
    @Test
    void testMaxMinAvgAll() throws Exception {

        mockMvc.perform(get("/api/statistics/maxMinAvgAll")).andExpect(status().isOk());
    }
    
    @Test
    void testMaxMinAvg() throws Exception {

        mockMvc.perform(get("/api/statistics/maxMinAvg/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }
    
    @Test
    void testPropTotalTime() throws Exception {

        mockMvc.perform(get("/api/statistics/propTotalTime/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testMaxMinAvgTime() throws Exception {

        mockMvc.perform(get("/api/statistics/maxMinAvgTime/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testMaxMinAvgAllTime() throws Exception {

        mockMvc.perform(get("/api/statistics/maxminavgtimeall")).andExpect(status().isOk());
    }



    



    
}
