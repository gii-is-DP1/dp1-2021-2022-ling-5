package com.example.accessingdatamysql.friendship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/api")
public class FriendshipController {
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private PlayerService playerService;

    @PostMapping(value = "/player/requester/{friendshipId}/friendships") // Map ONLY POST Requests
    public @ResponseBody Friendship addNewRequesterFriendship(@RequestBody Friendship friendship, @PathVariable Long playerId) {
        Optional<Player> requester = this.playerService.findPlayer(playerId);
        if (requester.isPresent()) {
        friendship.setRequester(requester.get());
        }
        return this.friendshipService.saveFriendship(friendship);
    }

    @GetMapping(value = "/friendships")
    public @ResponseBody Iterable<Friendship> getAllFriendships() {
        return this.friendshipService.findAllFriendships();
    }

    @GetMapping(value = "/friendships/{id}")
    public @ResponseBody Optional<Friendship> getFriendshipById(@PathVariable Long id) {
        return this.friendshipService.findFriendship(id);
    }

    @GetMapping(value = "/players/requester/{playerId}/friendships")
    public @ResponseBody List<Friendship> getAllFriendshipsByRequester(@PathVariable Long playerId) {
        Optional<Player> requester = this.playerService.findPlayer(playerId);
        if (requester.isPresent()) {
            return this.friendshipService.findAllFriendshipsByRequester(playerId);
        } else {
            return new ArrayList<Friendship>();
        }
    }

    @GetMapping(value = "/players/requested/{playerId}/friendships")
    public @ResponseBody List<Friendship> getAllFriendshipsByRequested(@PathVariable Long playerId) {
        Optional<Player> requested = this.playerService.findPlayer(playerId);
        if (requested.isPresent()) {
            return this.friendshipService.findAllFriendshipsByRequested(playerId);
        } else {
            return new ArrayList<Friendship>();
        }
    }    

    @DeleteMapping(value = "/friendships/{id}")
    public @ResponseBody String deleteFriendship(@PathVariable Long id) {
        this.friendshipService.deleteFriendship(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/friendships")
    public @ResponseBody String deleteAllFriendships() {
        this.friendshipService.deleteAllFriendships();
        return "Deleted all";
    }

    @DeleteMapping(value = "players/requester/{playerId}/friendships")
    public @ResponseBody String deleteAllFriendshipsByRequester(@PathVariable Long playerId) {
        this.friendshipService.deleteAllFriendshipsByRequester(playerId);
        return "Deleted all";
    }

    @DeleteMapping(value = "players/requested/{playerId}/friendships")
    public @ResponseBody String deleteAllFriendshipsByRequested(@PathVariable Long playerId) {
        this.friendshipService.deleteAllFriendshipsByRequested(playerId);
        return "Deleted all";
    }

    @PutMapping(value = "/friendships/{id}")
    public @ResponseBody Friendship updateFriendship(@RequestBody Friendship newFriendship, @PathVariable Long id) {
        this.friendshipService.findFriendship(id).map(friendship -> {
            friendship.setState(newFriendship.getState());
            return this.friendshipService.saveFriendship(friendship);
        }).orElseGet(() -> {
            newFriendship.setId(id);
            return this.friendshipService.saveFriendship(newFriendship);
        });
        return newFriendship;
    }

    @PutMapping(value = "/players/requester/{playerId}/friendships/{friendshipId}")
    public @ResponseBody String updateRequesterFriendship(@PathVariable Long friendshipId, @PathVariable Long playerId) {
        return this.playerService.findPlayer(playerId).map(requester -> {
            Optional<Friendship> optionalFriendship = this.friendshipService.findFriendship(friendshipId);
            if (optionalFriendship.isPresent()) {
                Friendship friendship = optionalFriendship.get();
                friendship.setRequester(requester);
                this.friendshipService.saveFriendship(friendship);
                return "Saved";
            } else {
                return "Friendship not found";
            }
        }).orElse("Requester not found");
    }

    @PutMapping(value = "/players/requested/{playerId}/friendships/{friendshipId}")
    public @ResponseBody String updateRequestedFriendship(@PathVariable Long friendshipId, @PathVariable Long playerId) {
        return this.playerService.findPlayer(playerId).map(requested -> {
            Optional<Friendship> optionalFriendship = this.friendshipService.findFriendship(friendshipId);
            if (optionalFriendship.isPresent()) {
                Friendship friendship = optionalFriendship.get();
                friendship.setRequester(requested);
                this.friendshipService.saveFriendship(friendship);
                return "Saved";
            } else {
                return "Friendship not found";
            }
        }).orElse("Requester not found");
    }
}
