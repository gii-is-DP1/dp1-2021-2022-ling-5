package com.example.accessingdatamysql.friendship;

import java.util.Optional;

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
    private FrindshipService friendshipService;

    @PostMapping(value = "/friendships") // Map ONLY POST Requests
    public @ResponseBody Friendship addNewFriendship(@RequestBody Friendship friendship) {
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
}
