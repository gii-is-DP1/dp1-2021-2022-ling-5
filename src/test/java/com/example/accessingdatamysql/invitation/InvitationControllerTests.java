package com.example.accessingdatamysql.invitation;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@WebMvcTest(controllers = InvitationController.class)
public class InvitationControllerTests {

    private static final Long TEST_INVITATION_ID = 1L;

    private static final Long TEST_PLAYER_ID = 1L;

    private static final Long TEST_PLAYER_ID_2 = 2L;

    @MockBean
    private InvitationService invitationService;

    @MockBean
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {

        Player player1 = new Player("name1", "surname1", "password1", "email1", "nickname1", PlayerState.NO_PLAY);
        player1.setId(TEST_PLAYER_ID);
        Player player2 = new Player("name2", "surname2", "password2", "email2", "nickname2", PlayerState.NO_PLAY);
        player2.setId(TEST_PLAYER_ID_2);
        given(this.playerService.findAllPlayers()).willReturn(Lists.newArrayList(player1, player2));

        Invitation invitation = new Invitation();
        invitation.setId(TEST_INVITATION_ID);
        invitation.setRequested(player2);
        invitation.setRequester(player1);
        given(this.invitationService.findAllInvitations()).willReturn(Lists.newArrayList(invitation));

        given(this.invitationService.findInvitation(TEST_INVITATION_ID)).willReturn(Optional.of(invitation));
        given(this.playerService.findPlayer(TEST_PLAYER_ID)).willReturn(Optional.of(player1));
        given(this.playerService.findPlayer(TEST_PLAYER_ID_2)).willReturn(Optional.of(player2));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/invitations")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/invitations/{invitationId}", TEST_INVITATION_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByIdBad() throws Exception {
        mockMvc.perform(get("/api/invitations/{invitationId}", TEST_INVITATION_ID + 9L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetByRequester() throws Exception {
        mockMvc.perform(get("/api/players/requester/{playerId}/invitations", TEST_PLAYER_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByRequesterBad() throws Exception {
        mockMvc.perform(get("/api/players/requester/{playerId}/invitations", TEST_PLAYER_ID + 8L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetByRequested() throws Exception {
        mockMvc.perform(get("/api/players/requested/{playerId}/invitations", TEST_PLAYER_ID_2))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByRequestedBad() throws Exception {
        mockMvc.perform(get("/api/players/requester/{playerId}/invitations", TEST_PLAYER_ID + 8L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Invitation invitation = new Invitation();
        mockMvc.perform(post("/api/players/requester/{requesterId}/requested/{requestedId}/invitations", TEST_PLAYER_ID,
                TEST_PLAYER_ID_2).contentType("application/json").content(objectMapper.writeValueAsString(invitation)))
                .andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccessBad() throws Exception {
        Invitation invitation = new Invitation();
        mockMvc.perform(
                post("/api/players/requester/{requesterId}/requested/{requestedId}/invitations", TEST_PLAYER_ID + 8L,
                        TEST_PLAYER_ID_2 + 8L).contentType("application/json")
                                .content(objectMapper.writeValueAsString(invitation)))
                .andExpect(status().isNotFound());
    }

}
