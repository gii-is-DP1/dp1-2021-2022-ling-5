package com.example.accessingdatamysql.ongoingminigame;

import java.util.List;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.card.CardService;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = OnGoingTorreInfernalController.class)
public class OnGoingTorreInfernalControllerTests {
    private static final Long test_Game_Id = 1L;
    private static final Long test_Player_Id = 1L;
    private static final Long test_Game_Id2 = 2L;

    @MockBean
    private OnGoingTorreInfernalService onGoingTorreInfernalService;

    @MockBean
    private GameService gameService;

    @MockBean
    private CardService cardService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OnGoingTorreInfernalController onGoingTorreInfernalController;

    @BeforeEach
    void setUp(){
        Request request = new Request();
        request.setGameId(test_Game_Id);
        OnGoingTorreInfernal onGoingTorreInfernal = onGoingTorreInfernalController.createGame(request);
        given(onGoingTorreInfernalController.createGame(request)).willReturn(onGoingTorreInfernal);
    }

    @Test
    void getAll() throws Exception{
        mockMvc.perform(get("/api/ongoingTorreInfernal")).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception{
        mockMvc.perform(get("/api/ongoingTorreInfernal/{gameId}", test_Game_Id)).andExpect(status().isOk());
    }

    @Test
    void getCard() throws Exception{
        mockMvc.perform(get("/api/ongoingTorreInfernal/{gameId}/card", test_Game_Id)).andExpect(status().isOk());
    }

    @Test
    void getPlayerCard() throws Exception{
        mockMvc.perform(get("/api/players/{playerId}/ongoingTorreInfernal/{gameId}", test_Player_Id, test_Game_Id))
        .andExpect(status().isOk());
    }

    @Test
    void getPoints() throws Exception{
        mockMvc.perform(get("/api/players/{playerId}/ongoingTorreInfernal/{gameId}/points", test_Player_Id, test_Game_Id))
        .andExpect(status().isOk());
    }

    @Test
    void newPlayerCard() throws Exception{
        RequestNewCard requestNewCard = new RequestNewCard();
        requestNewCard.setPlayerId(test_Player_Id);
        mockMvc.perform(put("/api/ongoingTorreInfernal/{gameId}/card/{playerId}", test_Game_Id, test_Player_Id)
        .contentType("application/json").content(objectMapper.writeValueAsString(requestNewCard))).andExpect(status().isOk());
    }

    @Test
    void addPoints() throws Exception{
        RequestAddPoints requestAddPoints = new RequestAddPoints();
        requestAddPoints.setPoints(10);
        mockMvc.perform(put("/api/players/{playerId}/ongoingTorreInfernal/{gameId}/points", test_Player_Id, test_Game_Id)
        .contentType("application/json").content(objectMapper.writeValueAsString(requestAddPoints)))
        .andExpect(status().isOk());
    }

    @Test
    void deleteGame() throws Exception{
        mockMvc.perform(delete("/api/ongoingTorreInfernal/{gameId}", test_Game_Id))
        .andExpect(status().isOk());
    }

    @Test 
    void deleteUnexistingGame() throws Exception{
        mockMvc.perform(delete("/api/ongoingTorreInfernal/{gameId}", test_Game_Id2))
        .andExpect(status().isOk());
    }

    @Test
    void createGame() throws Exception{
        Request request = new Request();
        request.setGameId(test_Game_Id2);
        mockMvc.perform(post("/api/ongoingTorreInfernal").contentType("application/json")
        .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
    }
}
