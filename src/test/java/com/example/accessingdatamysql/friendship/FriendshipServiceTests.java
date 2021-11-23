package com.example.accessingdatamysql.friendship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FriendshipServiceTests {
    @Autowired
    protected FriendshipService friendshipService;

    @Test
    @Transactional
    public void shouldInsertFriendship() {
        List<Friendship> friendships = this.friendshipService.findAllFriendships();
        int found = friendships.size();

        Friendship friendship = new Friendship(FriendshipState.REQUESTED);
        this.friendshipService.saveFriendship(friendship);
        assertNotEquals(friendship.getId(), 0L);

        friendships = this.friendshipService.findAllFriendships();
        assertEquals(friendships.size(), found + 1);
    }

    @Test
    public void shouldFindSingleFriendship() {
        Optional<Friendship> friendshipOpt = this.friendshipService.findFriendship(1L);
        if (friendshipOpt.isPresent()) {
            Friendship friendship = friendshipOpt.get();
            assertEquals(friendship.getState(), FriendshipState.REQUESTED);
        }
    }

    @Test
    @Transactional
    void shouldUpdateFriendship() {
        Optional<Friendship> friendship = this.friendshipService.findFriendship(1L);
        if (friendship.isPresent()) {
            FriendshipState newState = FriendshipState.FRIENDS;
            friendship.get().setState(newState);
            this.friendshipService.saveFriendship(friendship.get());

            friendship = this.friendshipService.findFriendship(1L);
            if (friendship.isPresent()) {
                assertEquals(friendship.get().getState(), newState);
            }
        }
    }

    @Test
    void shouldDeleteFriendship() {
        Friendship friendship = new Friendship(FriendshipState.REQUESTED);
        friendship = this.friendshipService.saveFriendship(friendship);
        List<Friendship> friendships = this.friendshipService.findAllFriendships();
        int found = friendships.size();

        this.friendshipService.deleteFriendship(friendship.getId());
        friendships = this.friendshipService.findAllFriendships();
        assertEquals(friendships.size(), found - 1);

    }

}
