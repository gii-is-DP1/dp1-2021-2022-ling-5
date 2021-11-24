package com.example.accessingdatamysql.figure;

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

@WebMvcTest(controllers = FigureController.class)
public class FigureControllerTests {

    private static final Long TEST_FIGURE_ID = 1L;

    @MockBean
    private FigureService figureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {

        Figure figure1 = new Figure();
        figure1.setName("mariposa");
        figure1.setId(TEST_FIGURE_ID);
        given(this.figureService.findAllFigures()).willReturn(Lists.newArrayList(figure1));

        given(this.figureService.findFigure(TEST_FIGURE_ID)).willReturn(Optional.of(figure1));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/figures")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/figures/{figureId}", TEST_FIGURE_ID)).andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Figure figure = new Figure("arbol");
        mockMvc.perform(
                post("/api/figures").contentType("application/json").content(objectMapper.writeValueAsString(figure)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/figures/{figureId}", TEST_FIGURE_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/figures")).andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Figure figure = new Figure("trebol");
        mockMvc.perform(put("/api/figures/{figureId}", TEST_FIGURE_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(figure))).andExpect(status().isOk());
    }
}
