package com.example.accessingdatamysql.user;

import com.example.accessingdatamysql.achievement.Achievement;
import com.example.accessingdatamysql.achievement.AchievementService;
import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.role.Role;
import com.example.accessingdatamysql.role.RoleService;
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

@WebMvcTest(controllers = PlayerController.class)
public class PlayerControllerTests {

    private static final Long TEST_PLAYER_ID = 1L;

    private static final Long TEST_FIGURE_ID = 1L;

    private static final Long TEST_ROLE_ID = 1L;

    private static final Long TEST_ACHIEVEMENT_ID = 1L;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private FigureService figureService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private AchievementService achievementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Player player = new Player("name1", "surname1", "password1", "email1", "nickname1", PlayerState.NO_PLAY);
        player.setId(TEST_PLAYER_ID);
        given(this.playerService.findAllPlayers()).willReturn(Lists.newArrayList(player));

        Figure figure = new Figure("abeja");
        figure.setId(TEST_FIGURE_ID);
        given(this.figureService.findAllFigures()).willReturn(Lists.newArrayList(figure));

        Role role = new Role("player");
        role.setId(TEST_ROLE_ID);
        given(this.roleService.findAllRoles()).willReturn(Lists.newArrayList(role));

        Achievement achievement = new Achievement("Streak10", "Streak 10 games");
        achievement.setId(TEST_ACHIEVEMENT_ID);
        given(this.achievementService.findAllAchievements()).willReturn(Lists.newArrayList(achievement));

        given(this.playerService.findPlayer(TEST_PLAYER_ID)).willReturn(Optional.of(player));
        given(this.figureService.findFigure(TEST_FIGURE_ID)).willReturn(Optional.of(figure));
        given(this.roleService.findRole(TEST_ROLE_ID)).willReturn(Optional.of(role));
        given(this.achievementService.findAchievement(TEST_ACHIEVEMENT_ID)).willReturn(Optional.of(achievement));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/players")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/players/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByRole() throws Exception {
        mockMvc.perform(get("/api/roles/{roleId}/players", TEST_ROLE_ID)).andExpect(status().isOk());
    }

    @Test
    void testCreationRole() throws Exception {
        Player player = new Player("name2", "surname2", "password2", "email2", "nickname2", PlayerState.NO_PLAY);
        mockMvc.perform(post("/api/roles/{roleId}/figures/{figureId}/players", TEST_ROLE_ID, TEST_FIGURE_ID)
                .contentType("application/json").content(objectMapper.writeValueAsString(player)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAchievementToPlayer() throws Exception {
        mockMvc.perform(
                post("/api/players/{playerId}/achievements/{achievementId}", TEST_PLAYER_ID, TEST_ACHIEVEMENT_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/players/{playerId}", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/players")).andExpect(status().isOk());
    }

    @Test
    void testDeleteAchievementFromPlayer() throws Exception {
        mockMvc.perform(
                delete("/api/players/{playerId}/achievements/{achievementId}", TEST_PLAYER_ID, TEST_ACHIEVEMENT_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Player player = new Player();
        player.setName("name3");
        mockMvc.perform(put("/api/players/{id}", TEST_PLAYER_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(player))).andExpect(status().isOk());
    }

    @Test
    void testUpdateFigurePlayer() throws Exception {
        mockMvc.perform(put("/api/figures/{figureId}/players/{playerId}", TEST_FIGURE_ID, TEST_PLAYER_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateRolePlayer() throws Exception {
        mockMvc.perform(put("/api/roles/{roleId}/players/{playerId}", TEST_ROLE_ID, TEST_PLAYER_ID))
                .andExpect(status().isOk());
    }
}
