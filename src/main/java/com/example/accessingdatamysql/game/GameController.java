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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/games", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Game addNewGame(@RequestBody Game game) {
        return this.gameService.saveGame(game);
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public @ResponseBody Iterable<Game> getAllGames() {
        return this.gameService.findAllGames();
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Game> getGameById(@PathVariable Long id) {
        return this.gameService.findGame(id);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteGame(@PathVariable Long id) {
        this.gameService.deleteGame(id);
        return "Deleted";
    }

    @RequestMapping(value = "/games", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllGames() {
        this.gameService.deleteAllGames();
        return "Deleted all";
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
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
