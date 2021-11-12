package com.example.accessingdatamysql.minigame;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class MinigameController {
    @Autowired
    private MinigameService minigameService;

    @RequestMapping(path = "/minigames", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Minigame addNewMinigame(@RequestBody Minigame minigame) {
        return this.minigameService.saveMinigame(minigame);
    }

    @RequestMapping(path = "/minigames", method = RequestMethod.GET)
    public @ResponseBody Iterable<Minigame> getAllMinigames() {
        return this.minigameService.findAllMinigames();
    }

    @RequestMapping(path = "/minigames/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Minigame> getMinigameById(@PathVariable Long id) {
        return this.minigameService.findMinigame(id);
    }

    @RequestMapping(path = "/minigames/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteMinigame(@PathVariable Long id) {
        this.minigameService.deleteMinigame(id);
        return "Deleted";
    }

    @RequestMapping(path = "/minigames", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllMinigames() {
        this.minigameService.deleteAllMinigames();
        return "Deleted all";
    }

    @RequestMapping(path = "/minigames/{id}", method = RequestMethod.PUT)
    public @ResponseBody Minigame updateMinigame(@RequestBody Minigame newMinigame, @PathVariable Long id) {
        this.minigameService.findMinigame(id).map(minigame -> {
            minigame.setName(newMinigame.getName());
            minigame.setDescription(newMinigame.getDescription());
            return this.minigameService.saveMinigame(minigame);
        }).orElseGet(() -> {
            newMinigame.setId(id);
            return this.minigameService.saveMinigame(newMinigame);
        });
        return newMinigame;
    }
}
