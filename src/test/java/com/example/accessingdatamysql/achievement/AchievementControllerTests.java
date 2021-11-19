package com.example.accessingdatamysql.achievement;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@WebMvcTest(controllers = AchievementController.class)
public class AchievementControllerTests {

    private static final Long TEST_ACHIEVEMENT_ID = 1L;

    private static final Long TEST_FIGURE_ID = 1L;

    @MockBean
    private AchievementService achievementService;

    @MockBean
    private FigureService figureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Achievement achievement1 = new Achievement();
        achievement1.setId(3L);
        achievement1.setName("Streak 10");
        given(this.achievementService.findAllAchievements()).willReturn(Lists.newArrayList(achievement1));
        given(this.figureService.findFigure(TEST_FIGURE_ID)).willReturn(Optional.of(new Figure()));
        given(this.achievementService.findAchievement(TEST_ACHIEVEMENT_ID)).willReturn(Optional.of(new Achievement()));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/achievements")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/achievements/{achievementId}", TEST_ACHIEVEMENT_ID)).andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Achievement achievement = new Achievement("Streak10", "Streak 10 games");
        mockMvc.perform(post("/api/achievements/figures/{figureId}", TEST_FIGURE_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(achievement))).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/achievements")).andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/achievements/{achievementId}", TEST_ACHIEVEMENT_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Achievement achievement = new Achievement("Streak100", "Streak 100 games");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/achievements/{achievementId}", TEST_ACHIEVEMENT_ID)
                .contentType("application/json").content(objectMapper.writeValueAsString(achievement)))
                .andExpect(status().isOk());
    }

}
