package com.example.accessingdatamysql.game;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.minigame.Minigame;
import com.example.accessingdatamysql.minigame.MinigameService;
import com.example.accessingdatamysql.result.Result;
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
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MinigameService minigameService;

    @PostMapping(value = "/games") // Map ONLY POST Requests
    public @ResponseBody Game addNewGame(@RequestBody Game game) {
        game.setMinigames(new ArrayList<Minigame>());
        game.setPlayers(new ArrayList<Player>());
        game.setResults(new ArrayList<Result>());

        return this.gameService.saveGame(game);
    }

    @PostMapping(value = "/games/{gameId}/players/{playerId}")
    public @ResponseBody Game addNewPlayerToGame(@PathVariable Long gameId, @PathVariable Long playerId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (!game.isPresent())
            return null;
        if (player.isPresent()) {
            if (player.get().getGamesPlayed() == null)
                player.get().setGamesPlayed(new ArrayList<Game>());
            if (game.get().getPlayers() == null)
                game.get().setPlayers(new ArrayList<Player>());

            player.get().getGamesPlayed().add(game.get());
            game.get().getPlayers().add(player.get());
        }
        return this.gameService.saveGame(game.get());
    }

    @PostMapping(value = "/games/{gameId}/minigames/{minigameId}")
    public @ResponseBody Game addNewMinigameToGame(@PathVariable Long gameId, @PathVariable Long minigameId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Minigame> minigame = this.minigameService.findMinigame(minigameId);
        if (!game.isPresent())
            return null;
        if (minigame.isPresent()) {
            if (minigame.get().getGames() == null)
                minigame.get().setGames(new ArrayList<Game>());
            if (game.get().getMinigames() == null)
                game.get().setMinigames(new ArrayList<Minigame>());

            minigame.get().getGames().add(game.get());
            game.get().getMinigames().add(minigame.get());

        }
        return this.gameService.saveGame(game.get());
    }

    @GetMapping(value = "/games")
    public @ResponseBody Iterable<Game> getAllGames() {
        return this.gameService.findAllGames();
    }

    @GetMapping(value = "/games/{id}")
    public @ResponseBody Optional<Game> getGameById(@PathVariable Long id) {
        return this.gameService.findGame(id);
    }

    @GetMapping(value = "/games/names/{name}")
    public @ResponseBody Optional<Game> getGameByName(@PathVariable String name) {
        return this.gameService.findGameByName(name);
    }

    @DeleteMapping(value = "/games/{id}")
    public @ResponseBody String deleteGame(@PathVariable Long id) {
        this.gameService.deleteGame(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/games")
    public @ResponseBody String deleteAllGames() {
        this.gameService.deleteAllGames();
        return "Deleted all";
    }

    @DeleteMapping(value = "/games/{gameId}/players/{playerId}")
    public @ResponseBody String deletePlayerFromGame(@PathVariable Long gameId, @PathVariable Long playerId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (!game.isPresent())
            return "Game not found";
        if (!player.isPresent())
            return "Player not found";

        if (game.get().getPlayers() == null) {
            game.get().setPlayers(new ArrayList<Player>());
            if (player.get().getGamesPlayed() == null)
                player.get().setGamesPlayed(new ArrayList<Game>());
            return "This game doesn't have players";
        }

        game.get().getPlayers().remove(player.get());
        player.get().getGamesPlayed().remove(game.get());
        return "Player deleted from game";

    }

    @DeleteMapping(value = "/games/{gameId}/minigames/{minigameId}")
    public @ResponseBody String deleteMinigameFromGame(@PathVariable Long gameId, @PathVariable Long minigameId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Minigame> minigame = this.minigameService.findMinigame(minigameId);
        if (!game.isPresent())
            return "Game not found";
        if (!minigame.isPresent())
            return "Minigame not found";

        if (game.get().getMinigames() == null) {
            game.get().setMinigames(new ArrayList<Minigame>());
            if (minigame.get().getGames() == null)
                minigame.get().setGames(new ArrayList<Game>());
            return "This game doesn't have minigames";
        }

        game.get().getMinigames().remove(minigame.get());
        minigame.get().getGames().remove(game.get());
        return "Minigame deleted from game";
    }

    @PutMapping(value = "/games/{id}")
    public @ResponseBody Game updateGame(@RequestBody Game newGame, @PathVariable Long id) {
        this.gameService.findGame(id).map(game -> {
            game.setName(newGame.getName());
            game.setState(newGame.getState());
            game.setStartTime(newGame.getStartTime());
            game.setEndTime(newGame.getEndTime());
            game.setCreator(newGame.getCreator());
            game.setWinner(newGame.getWinner());
            return this.gameService.saveGame(game);
        }).orElse(null);
        return null;
    }
}
