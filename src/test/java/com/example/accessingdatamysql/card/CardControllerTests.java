package com.example.accessingdatamysql.card;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
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

import java.util.Optional;

@WebMvcTest(controllers = CardController.class)
public class CardControllerTests {

    private static final Long TEST_CARD_ID = 1L;

    private static final Long TEST_FIGURE_ID = 1L;

    @MockBean
    private CardService cardService;

    @MockBean
    private FigureService figureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Card card1 = new Card();
        card1.setName("c03");
        card1.setId(TEST_CARD_ID);
        given(this.cardService.findAllCards()).willReturn(Lists.newArrayList(card1));

        Figure figure1 = new Figure();
        figure1.setName("mariposa");
        figure1.setId(TEST_FIGURE_ID);
        given(this.figureService.findAllFigures()).willReturn(Lists.newArrayList(figure1));

        given(this.figureService.findFigure(TEST_FIGURE_ID)).willReturn(Optional.of(figure1));
        given(this.cardService.findCard(TEST_CARD_ID)).willReturn(Optional.of(card1));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/cards")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/cards/{cardId}", TEST_CARD_ID)).andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Card card = new Card("c01");
        mockMvc.perform(
                post("/api/cards").contentType("application/json").content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddFigureToCard() throws Exception {
        mockMvc.perform(post("/api/cards/{cardId}/figures/{figureId}", TEST_CARD_ID, TEST_FIGURE_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAFigureFromCard() throws Exception {
        mockMvc.perform(delete("/api//cards/{cardId}/figures/{figureId}", TEST_CARD_ID, TEST_FIGURE_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/cards/{cardId}", TEST_CARD_ID)).andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/cards")).andExpect(status().isNoContent());
    }

    @Test
    void testUpdate() throws Exception {
        Card card = new Card("c02");
        mockMvc.perform(put("/api/cards/{cardId}", TEST_CARD_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(card))).andExpect(status().isOk());
    }
}
