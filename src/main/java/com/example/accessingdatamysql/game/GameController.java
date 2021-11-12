package com.example.accessingdatamysql.game;

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
@RequestMapping(path = "/api")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping(path = "/games") // Map ONLY POST Requests
    public @ResponseBody Game addNewGame(@RequestBody Game game) {
        return this.gameService.saveGame(game);
    }

    @GetMapping(path = "/games")
    public @ResponseBody Iterable<Game> getAllGames() {
        return this.gameService.findAllGames();
    }

    @GetMapping(path = "/games/{id}")
    public @ResponseBody Optional<Game> getGameById(@PathVariable Long id) {
        return this.gameService.findGame(id);
    }

    @DeleteMapping(path = "/games/{id}")
    public @ResponseBody String deleteGame(@PathVariable Long id) {
        this.gameService.deleteGame(id);
        return "Deleted";
    }

    @DeleteMapping(path = "/games")
    public @ResponseBody String deleteAllGames() {
        this.gameService.deleteAllGames();
        return "Deleted all";
    }

    @PutMapping("/games/{id}")
    public @ResponseBody Game updateGame(@RequestBody Game newGame, @PathVariable Long id) {
        this.gameService.findGame(id).map(game -> {
            game.setName(newGame.getName());
            game.setState(newGame.getState());
            game.setStartTime(newGame.getStartTime());
            game.setEndTime(newGame.getEndTime());
            return this.gameService.saveGame(game);
        }).orElseGet(() -> {
            newGame.setId(id);
            return this.gameService.saveGame(newGame);
        });
        return newGame;
    }
}