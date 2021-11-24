package com.example.accessingdatamysql.minigame;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.game.Game;

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
public class MinigameController {
    @Autowired
    private MinigameService minigameService;

    @PostMapping(value = "/minigames") // Map ONLY POST Requests
    public @ResponseBody Minigame addNewMinigame(@RequestBody Minigame minigame) {
        minigame.setGames(new ArrayList<Game>());

        return this.minigameService.saveMinigame(minigame);
    }

    @GetMapping(value = "/minigames")
    public @ResponseBody Iterable<Minigame> getAllMinigames() {
        return this.minigameService.findAllMinigames();
    }

    @GetMapping(value = "/minigames/{id}")
    public @ResponseBody Optional<Minigame> getMinigameById(@PathVariable Long id) {
        return this.minigameService.findMinigame(id);
    }

    @DeleteMapping(value = "/minigames/{id}")
    public @ResponseBody String deleteMinigame(@PathVariable Long id) {
        this.minigameService.deleteMinigame(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/minigames")
    public @ResponseBody String deleteAllMinigames() {
        this.minigameService.deleteAllMinigames();
        return "Deleted all";
    }

    @PutMapping(value = "/minigames/{id}")
    public @ResponseBody Minigame updateMinigame(@RequestBody Minigame newMinigame, @PathVariable Long id) {
        this.minigameService.findMinigame(id).map(minigame -> {
            minigame.setName(newMinigame.getName());
            minigame.setDescription(newMinigame.getDescription());
            return this.minigameService.saveMinigame(minigame);
        }).orElse(null);
        return null;
    }
}
