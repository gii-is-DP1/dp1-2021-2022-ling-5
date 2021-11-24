package com.example.accessingdatamysql.modification;

import com.example.accessingdatamysql.user.Admin;
import com.example.accessingdatamysql.user.AdminService;
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

@WebMvcTest(controllers = ModificationController.class)
public class ModificationControllerTests {

    private static final Long TEST_MODIFICATION_ID = 1L;

    private static final Long TEST_PLAYER_ID = 1L;

    private static final Long TEST_ADMIN_ID = 1L;

    @MockBean
    private ModificationService modificationService;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Modification modification = new Modification(new Date(System.currentTimeMillis()), "name", "uno", "UNO");
        modification.setId(TEST_MODIFICATION_ID);
        given(this.modificationService.findAllModifications()).willReturn(Lists.newArrayList(modification));

        Player player = new Player("name1", "surname1", "password1", "email1", "nickname1", PlayerState.NO_PLAY);
        player.setId(TEST_PLAYER_ID);
        given(this.playerService.findAllPlayers()).willReturn(Lists.newArrayList(player));

        Admin admin = new Admin("name2", "surname2", "password2", "email2", "nickname2");
        admin.setId(TEST_ADMIN_ID);
        given(this.adminService.findAllAdmins()).willReturn(Lists.newArrayList(admin));

        given(this.modificationService.findModification(TEST_MODIFICATION_ID)).willReturn(Optional.of(modification));
        given(this.playerService.findPlayer(TEST_PLAYER_ID)).willReturn(Optional.of(player));
        given(this.adminService.findAdmin(TEST_ADMIN_ID)).willReturn(Optional.of(admin));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/modifications")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/modifications/{modificationId}", TEST_MODIFICATION_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByPlayer() throws Exception {
        mockMvc.perform(get("/api/players/{playerId}/modifications", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByAdmin() throws Exception {
        mockMvc.perform(get("/api/admins/{adminId}/modifications", TEST_ADMIN_ID)).andExpect(status().isOk());
    }

    @Test
    void testCreationModificationPlayer() throws Exception {
        Modification modification = new Modification(new Date(System.currentTimeMillis()), "name", "dos", "DOS");
        mockMvc.perform(post("/api/players/{playerId}/modifications", TEST_PLAYER_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(modification))).andExpect(status().isOk());
    }

    @Test
    void testCreationModificationAdmin() throws Exception {
        Modification modification = new Modification(new Date(System.currentTimeMillis()), "name", "tres", "TRES");
        mockMvc.perform(post("/api/admins/{adminId}/modifications", TEST_ADMIN_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(modification))).andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/modifications/{modificationId}", TEST_MODIFICATION_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/modifications")).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllByPlayer() throws Exception {
        mockMvc.perform(delete("/api/players/{playerId}/modifications", TEST_PLAYER_ID)).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllByAdmin() throws Exception {
        mockMvc.perform(delete("/api/admins/{adminId}/modifications", TEST_ADMIN_ID)).andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Modification modification = new Modification();
        modification.setWhat("surname");
        mockMvc.perform(put("/api/modifications/{id}", TEST_MODIFICATION_ID).contentType("application/json")
                .content(objectMapper.writeValueAsString(modification))).andExpect(status().isOk());
    }
}
