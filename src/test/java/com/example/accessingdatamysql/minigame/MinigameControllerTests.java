package com.example.accessingdatamysql.minigame;

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

@WebMvcTest(controllers = MinigameController.class)
public class MinigameControllerTests {

    private static final Long TEST_MINIGAME_ID = 1L;

    @MockBean
    private MinigameService minigameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Minigame minigame = new Minigame("juego1", "descripcion juego 1");
        minigame.setId(TEST_MINIGAME_ID);
        given(this.minigameService.findAllMinigames()).willReturn(Lists.newArrayList(minigame));

        given(this.minigameService.findMinigame(TEST_MINIGAME_ID)).willReturn(Optional.of(minigame));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/minigames")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/minigames/{minigameId}", TEST_MINIGAME_ID)).andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Minigame minigame = new Minigame("juego2", "descripcion juego 2");
        mockMvc.perform(post("/api/minigames").contentType("application/json")
                .content(objectMapper.writeValueAsString(minigame))).andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/minigames/{minigameId}", TEST_MINIGAME_ID)).andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/minigames")).andExpect(status().isNoContent());
    }

    @Test
    void testUpdate() throws Exception {
        Minigame minigame = new Minigame();
        minigame.setName("juego3");
        mockMvc.perform(put("/api/minigames/{id}", TEST_MINIGAME_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(minigame))).andExpect(status().isOk());
    }
}
