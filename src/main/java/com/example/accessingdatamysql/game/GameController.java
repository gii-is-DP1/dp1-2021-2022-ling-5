package com.example.accessingdatamysql.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.minigame.Minigame;
import com.example.accessingdatamysql.minigame.MinigameService;
import com.example.accessingdatamysql.result.Result;
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
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MinigameService minigameService;

    @PostMapping(value = "/games") // Map ONLY POST Requests
    public @ResponseBody Game addNewGame(@RequestBody Game game) {
        try {
            game.setMinigames(new ArrayList<Minigame>());
            game.setPlayers(new ArrayList<Player>());
            List<Player> players = game.getPlayers();
            players.add(this.playerService.findPlayer(game.getCreator()).get());
            game.setPlayers(players);
            Player creator = this.playerService.findPlayer(game.getCreator()).get();
            if (creator.getGamesPlayed() == null) {
                creator.setGamesPlayed(new ArrayList<Game>());
            }
            List<Game> gamesPlayed = creator.getGamesPlayed();
            game.setResults(new ArrayList<Result>());
            gamesPlayed.add(game);
            creator.setGamesPlayed(gamesPlayed);

            return this.gameService.saveGame(game);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/games/{gameId}/players/{playerId}")
    public @ResponseBody Game addNewPlayerToGame(@PathVariable Long gameId, @PathVariable Long playerId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (!game.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        if (player.isPresent()) {
            try {
                if (player.get().getGamesPlayed() == null)
                    player.get().setGamesPlayed(new ArrayList<Game>());
                if (game.get().getPlayers() == null)
                    game.get().setPlayers(new ArrayList<Player>());

                if (!player.get().getGamesPlayed().contains(game.get()))
                    player.get().getGamesPlayed().add(game.get());
                if (!game.get().getPlayers().contains(player.get()))
                    game.get().getPlayers().add(player.get());
                return this.gameService.saveGame(game.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
    }

    @PostMapping(value = "/games/{gameId}/minigames/{minigameId}")
    public @ResponseBody Game addNewMinigameToGame(@PathVariable Long gameId, @PathVariable Long minigameId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Minigame> minigame = this.minigameService.findMinigame(minigameId);
        if (!game.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        if (minigame.isPresent()) {
            try {
                if (minigame.get().getGames() == null)
                    minigame.get().setGames(new ArrayList<Game>());
                if (game.get().getMinigames() == null)
                    game.get().setMinigames(new ArrayList<Minigame>());

                if (!minigame.get().getGames().contains(game.get()))
                    minigame.get().getGames().add(game.get());
                if (!game.get().getMinigames().contains(minigame.get()))
                    game.get().getMinigames().add(minigame.get());
                return this.gameService.saveGame(game.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Minigame not found");
    }

    @GetMapping(value = "/games")
    public @ResponseBody Iterable<Game> getAllGames() {
        return this.gameService.findAllGames();
    }

    @GetMapping(value = "/games/{id}")
    public @ResponseBody Game getGameById(@PathVariable Long id) {
        Optional<Game> gOptional = this.gameService.findGame(id);
        if (gOptional.isPresent())
            return gOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
    }

    @GetMapping(value = "/games/{id}/players")
    public @ResponseBody List<Player> getPlayersByGame(@PathVariable Long id) {
        Optional<Game> gOptional = this.gameService.findGame(id);
        if (gOptional.isPresent())
            return gOptional.get().getPlayers();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
    }

    @GetMapping(value = "/games/names/{name}")
    public @ResponseBody Game getGameByName(@PathVariable String name) {
        Optional<Game> gOptional = this.gameService.findGameByName(name);
        if (gOptional.isPresent())
            return gOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
    }

    @DeleteMapping(value = "/games/{id}")
    public @ResponseBody void deleteGame(@PathVariable Long id) {
        Optional<Game> gOptional = this.gameService.findGame(id);
        if (gOptional.isPresent()) {
            this.gameService.deleteGame(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Game deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");

    }

    @DeleteMapping(value = "/games")
    public @ResponseBody void deleteAllGames() {
        this.gameService.deleteAllGames();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Games deleted");
    }

    @DeleteMapping(value = "/games/{gameId}/players/{playerId}")
    public @ResponseBody void deletePlayerFromGame(@PathVariable Long gameId, @PathVariable Long playerId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (!game.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        if (!player.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");

        if (game.get().getPlayers() == null) {
            game.get().setPlayers(new ArrayList<Player>());
            if (player.get().getGamesPlayed() == null)
                player.get().setGamesPlayed(new ArrayList<Game>());
            throw new ResponseStatusException(HttpStatus.OK, "Game doesn't have players to remove");
        }

        game.get().getPlayers().remove(player.get());
        player.get().getGamesPlayed().remove(game.get());
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Player deleted from game");

    }

    @DeleteMapping(value = "/games/{gameId}/minigames/{minigameId}")
    public @ResponseBody String deleteMinigameFromGame(@PathVariable Long gameId, @PathVariable Long minigameId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Minigame> minigame = this.minigameService.findMinigame(minigameId);
        if (!game.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        if (!minigame.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Minigame not found");

        if (game.get().getMinigames() == null) {
            game.get().setMinigames(new ArrayList<Minigame>());
            if (minigame.get().getGames() == null)
                minigame.get().setGames(new ArrayList<Game>());
            throw new ResponseStatusException(HttpStatus.OK, "Game doesn't have minigames to remove");
        }

        game.get().getMinigames().remove(minigame.get());
        minigame.get().getGames().remove(game.get());
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Minigame deleted from game");
    }

    @PutMapping(value = "/games/{id}")
    public @ResponseBody Game updateGame(@RequestBody Game newGame, @PathVariable Long id) {
        Optional<Game> gOptional = this.gameService.findGame(id);
        if (!gOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        try {
            Game game = gOptional.get();
            game.setName(newGame.getName());
            game.setState(newGame.getState());
            game.setStartTime(newGame.getStartTime());
            game.setEndTime(newGame.getEndTime());
            game.setCreator(newGame.getCreator());
            game.setWinner(newGame.getWinner());
            return this.gameService.saveGame(game);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
