package com.example.accessingdatamysql.minigame;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.game.Game;

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
public class MinigameController {
    @Autowired
    private MinigameService minigameService;

    @PostMapping(value = "/minigames")
    public @ResponseBody Minigame addNewMinigame(@RequestBody Minigame minigame) {
        try {
            minigame.setGames(new ArrayList<Game>());
            return this.minigameService.saveMinigame(minigame);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/minigames")
    public @ResponseBody Iterable<Minigame> getAllMinigames() {
        return this.minigameService.findAllMinigames();
    }

    @GetMapping(value = "/minigames/{id}")
    public @ResponseBody Minigame getMinigameById(@PathVariable Long id) {
        Optional<Minigame> mOptional = this.minigameService.findMinigame(id);
        if (mOptional.isPresent())
            return mOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Minigame not found");
    }

    @DeleteMapping(value = "/minigames/{id}")
    public @ResponseBody void deleteMinigame(@PathVariable Long id) {
        Optional<Minigame> mOptional = this.minigameService.findMinigame(id);
        if (mOptional.isPresent()) {
            this.minigameService.deleteMinigame(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Minigame deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Minigame not found");

    }

    @DeleteMapping(value = "/minigames")
    public @ResponseBody void deleteAllMinigames() {
        this.minigameService.deleteAllMinigames();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Minigames deleted");
    }

    @PutMapping(value = "/minigames/{id}")
    public @ResponseBody Minigame updateMinigame(@RequestBody Minigame newMinigame, @PathVariable Long id) {
        Optional<Minigame> mOptional = this.minigameService.findMinigame(id);
        if (!mOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Minigame not found");
        try {
            Minigame minigame = mOptional.get();
            minigame.setName(newMinigame.getName());
            minigame.setDescription(newMinigame.getDescription());
            return this.minigameService.saveMinigame(minigame);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
