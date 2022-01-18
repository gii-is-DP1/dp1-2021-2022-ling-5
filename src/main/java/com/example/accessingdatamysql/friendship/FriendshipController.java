package com.example.accessingdatamysql.friendship;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/api")
public class FriendshipController {
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private PlayerService playerService;

    @PostMapping(value = "/players/requester/{requesterId}/requested/{requestedId}/friendships")
    public @ResponseBody Friendship addFriendship(@RequestBody Friendship friendship, @PathVariable Long requesterId,
            @PathVariable Long requestedId) {
        Optional<Player> requester = this.playerService.findPlayer(requesterId);
        Optional<Player> requested = this.playerService.findPlayer(requestedId);
        if (!requester.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found");
        if (!requested.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested not found");
        try {
            friendship.setRequester(requester.get());
            friendship.setRequested(requested.get());
            return this.friendshipService.saveFriendship(friendship);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/friendships")
    public @ResponseBody Iterable<Friendship> getAllFriendships() {
        return this.friendshipService.findAllFriendships();
    }

    @GetMapping(value = "/friendships/{id}")
    public @ResponseBody Friendship getFriendshipById(@PathVariable Long id) {
        Optional<Friendship> friendshipOpt = this.friendshipService.findFriendship(id);
        if (friendshipOpt.isPresent())
            return friendshipOpt.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Friendship not found");
    }

    @GetMapping(value = "/players/requester/{playerId}/friendships")
    public @ResponseBody List<Friendship> getAllFriendshipsByRequester(@PathVariable Long playerId) {
        Optional<Player> requester = this.playerService.findPlayer(playerId);
        if (requester.isPresent()) {
            return this.friendshipService.findAllFriendshipsByRequester(playerId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found");
    }

    @GetMapping(value = "/players/requested/{playerId}/friendships")
    public @ResponseBody List<Friendship> getAllFriendshipsByRequested(@PathVariable Long playerId) {
        Optional<Player> requested = this.playerService.findPlayer(playerId);
        if (requested.isPresent()) {
            return this.friendshipService.findAllFriendshipsByRequested(playerId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested not found");
    }

    @DeleteMapping(value = "/friendships/{id}")
    public @ResponseBody void deleteFriendship(@PathVariable Long id) {
        Optional<Friendship> fOptional = this.friendshipService.findFriendship(id);
        if (fOptional.isPresent()) {
            this.friendshipService.deleteFriendship(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Friendship deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Friendship not found");
    }

    @DeleteMapping(value = "/friendships")
    public @ResponseBody void deleteAllFriendships() {
        this.friendshipService.deleteAllFriendships();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Friendships deleted");
    }

    @DeleteMapping(value = "/players/requester/{playerId}/friendships")
    public @ResponseBody void deleteAllFriendshipsByRequester(@PathVariable Long playerId) {
        Optional<Player> pOptional = this.playerService.findPlayer(playerId);
        if (pOptional.isPresent()) {
            this.friendshipService.deleteAllFriendshipsByRequester(playerId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Friendship deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found");

    }

    @DeleteMapping(value = "/players/requested/{playerId}/friendships")
    public @ResponseBody String deleteAllFriendshipsByRequested(@PathVariable Long playerId) {
        Optional<Player> pOptional = this.playerService.findPlayer(playerId);
        if (pOptional.isPresent()) {
            this.friendshipService.deleteAllFriendshipsByRequested(playerId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Friendship deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested not found");
    }

    @PutMapping(value = "/players/requested/{requestedId}/friendships/{id}")
    public @ResponseBody Friendship updateFriendship(@RequestBody Friendship newFriendship,
            @PathVariable Long requestedId, @PathVariable Long id) {
        Optional<Friendship> fOptional = this.friendshipService.findFriendship(id);
        if (!fOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Friendship not found");
        try {
            Friendship friendship = fOptional.get();
            if (friendship.getRequested().getId() == requestedId) {
                friendship.setState(newFriendship.getState());
                return this.friendshipService.saveFriendship(friendship);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The requested given doesn't match with the requested in the friendship");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
