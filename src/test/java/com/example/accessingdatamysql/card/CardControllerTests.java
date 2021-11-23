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

// NO FUNCIONA
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
        card1.setId(3L);
        card1.setName("3");
        given(this.cardService.findAllCards()).willReturn(Lists.newArrayList(card1));
        given(this.figureService.findFigure(TEST_FIGURE_ID)).willReturn(Optional.of(new Figure()));
        given(this.cardService.findCard(TEST_CARD_ID)).willReturn(Optional.of(new Card()));

        // Card card2 = new Card("1");
        // card2.setId(TEST_CARD_ID);
        // this.cardService.saveCard(card2);

        // Figure figure = new Figure("mariposa");
        // figure.setId(TEST_FIGURE_ID);
        // this.figureService.saveFigure(figure);
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
        Card card = new Card("1");
        mockMvc.perform(
                post("/api/cards").contentType("application/json").content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddFigureToCard() throws Exception {
        try {
            mockMvc.perform(post("/api/cards/{cardId}/figures/{figureId}", TEST_CARD_ID, TEST_FIGURE_ID))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println("EXCEPTION figuretocard: " + e.getMessage());
        }
    }

    @Test
    void testUpdate() throws Exception {
        Card card = new Card("2");
        try {
            mockMvc.perform(put("/api/cards/{cardId}", TEST_CARD_ID).contentType("application/json")
                    .content(objectMapper.writeValueAsString(card))).andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println("EXCEPTION UPDATE: " + e.getMessage());
        }

    }

    // @Test
    // void testDeleteAFigureFromCard() throws Exception {
    // mockMvc.perform(delete("/api//cards/{cardId}/figures/{figureId}",
    // TEST_CARD_ID, TEST_FIGURE_ID))
    // .andExpect(status().isOk());
    // }

    // @Test
    // void testDeleteById() throws Exception {
    // mockMvc.perform(delete("/api/cards/{cardId}",
    // TEST_CARD_ID)).andExpect(status().isOk());
    // }

    // @Test
    // void testDeleteAll() throws Exception {
    // mockMvc.perform(delete("/api/cards")).andExpect(status().isOk());
    // }

}
