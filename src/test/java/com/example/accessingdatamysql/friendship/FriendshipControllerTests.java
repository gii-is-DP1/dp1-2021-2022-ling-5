package com.example.accessingdatamysql.friendship;

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

import java.util.Optional;
@WebMvcTest(controllers = FriendshipController.class)
public class FriendshipControllerTests {

    private static final Long TEST_FRIENDSHIP_ID = 1L;

    private static final Long TEST_PLAYER_ID = 1L;

    private static final Long TEST_PLAYER_ID_2 = 2L;

    @MockBean
    private FriendshipService friendshipService;

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
        
        Friendship friendship = new Friendship(FriendshipState.REQUESTED);
        friendship.setId(TEST_FRIENDSHIP_ID);
        friendship.setRequested(player2);
        friendship.setRequester(player1);
        given(this.friendshipService.findAllFriendships()).willReturn(Lists.newArrayList(friendship));

        given(this.friendshipService.findFriendship(TEST_FRIENDSHIP_ID)).willReturn(Optional.of(friendship));
        given(this.playerService.findPlayer(TEST_PLAYER_ID)).willReturn(Optional.of(player1));
        given(this.playerService.findPlayer(TEST_PLAYER_ID_2)).willReturn(Optional.of(player2));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/friendships")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/friendships/{friendshipId}", TEST_FRIENDSHIP_ID)).andExpect(status().isOk());
    }

    @Test
    void testGetByIdBad() throws Exception {
        mockMvc.perform(get("/api/friendships/{friendshipId}", TEST_FRIENDSHIP_ID+9L)).andExpect(status().isNotFound());
    }

    @Test
    void testGetByRequester() throws Exception {
        mockMvc.perform(get("/api/players/requester/{playerId}/friendships", TEST_PLAYER_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByRequesterBad() throws Exception {
        mockMvc.perform(get("/api/players/requester/{playerId}/friendships", TEST_PLAYER_ID+8L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetByRequested() throws Exception {
        mockMvc.perform(get("/api/players/requested/{playerId}/friendships", TEST_PLAYER_ID_2))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByRequestedBad() throws Exception {
        mockMvc.perform(get("/api/players/requester/{playerId}/friendships", TEST_PLAYER_ID+8L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testProcessCreationSuccess() throws Exception {
        Friendship friendship = new Friendship(FriendshipState.REQUESTED);
        mockMvc.perform(post("/api/players/requester/{requesterId}/requested/{requestedId}/friendships", TEST_PLAYER_ID,
                TEST_PLAYER_ID_2).contentType("application/json").content(objectMapper.writeValueAsString(friendship)))
                .andExpect(status().isOk());
    }

    @Test
    void testProcessCreationSuccessBad() throws Exception {
        Friendship friendship = new Friendship(FriendshipState.REQUESTED);
        mockMvc.perform(post("/api/players/requester/{requesterId}/requested/{requestedId}/friendships", TEST_PLAYER_ID+8L,
                TEST_PLAYER_ID_2+8L).contentType("application/json").content(objectMapper.writeValueAsString(friendship)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/friendships/{friendshipId}", TEST_FRIENDSHIP_ID)).andExpect(status().isNoContent());
    }

    @Test
    void testDeleteByIdBad() throws Exception {
        mockMvc.perform(delete("/api/friendships/{friendshipId}", TEST_FRIENDSHIP_ID+8L)).andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/friendships")).andExpect(status().isNoContent());
    }

    @Test
    void testDeleteByRequester() throws Exception {
        mockMvc.perform(delete("/api/players/requester/{playerId}/friendships", TEST_PLAYER_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteByRequesterBad() throws Exception {
        mockMvc.perform(delete("/api/players/requester/{playerId}/friendships", TEST_PLAYER_ID+6L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteByRequested() throws Exception {
        mockMvc.perform(delete("/api/players/requested/{playerId}/friendships", TEST_PLAYER_ID_2))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteByRequestedBad() throws Exception {
        mockMvc.perform(delete("/api/players/requested/{playerId}/friendships", TEST_PLAYER_ID_2+6L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdate() throws Exception {
        Friendship friendship = new Friendship(FriendshipState.FRIENDS);
        mockMvc.perform(
                put("/api/players/requested/{requestedId}/friendships/{id}", TEST_PLAYER_ID_2, TEST_FRIENDSHIP_ID)
                        .contentType("application/json").content(objectMapper.writeValueAsString(friendship)))
                .andExpect(status().isOk());
    }
}
