package com.example.accessingdatamysql.result;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameService;
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
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/games/{gameId}/players/{playerId}/results")
    public @ResponseBody Result addNewResult(@RequestBody Result result, @PathVariable Long gameId,
            @PathVariable Long playerId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (!game.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        if (!player.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        try {
            result.setGame(game.get());
            result.setPlayer(player.get());
            return this.resultService.saveResult(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/results")
    public @ResponseBody Iterable<Result> getAllResults() {
        return this.resultService.findAllResults();
    }

    @GetMapping(value = "/results/{id}")
    public @ResponseBody Result getResultById(@PathVariable Long id) {
        Optional<Result> rOptional = this.resultService.findResult(id);
        if (rOptional.isPresent())
            return rOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found");
    }

    @GetMapping(value = "/games/{gameId}/results")
    public @ResponseBody List<Result> getAllResultsByGame(@PathVariable Long gameId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        if (game.isPresent()) {
            return this.resultService.findAllResultsByGame(gameId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
    }

    @GetMapping(value = "/players/{playerId}/results")
    public @ResponseBody List<Result> getAllResultsByPlayer(@PathVariable Long playerId) {
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (player.isPresent()) {
            return this.resultService.findAllResultsByPlayer(playerId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
    }

    @DeleteMapping(value = "/results/{id}")
    public @ResponseBody void deleteResult(@PathVariable Long id) {
        Optional<Result> rOptional = this.resultService.findResult(id);
        if (rOptional.isPresent()) {
            this.resultService.deleteResult(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Result deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found");

    }

    @DeleteMapping(value = "/results")
    public @ResponseBody void deleteAllResults() {
        this.resultService.deleteAllResults();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Results deleted");
    }

    @DeleteMapping(value = "/games/{gameId}/results")
    public @ResponseBody void deleteAllResultsByGame(@PathVariable Long gameId) {
        this.resultService.deleteAllResultsByGame(gameId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Results deleted");
    }

    @DeleteMapping(value = "/players/{playerId}/results")
    public @ResponseBody void deleteAllResultsByPlayer(@PathVariable Long playerId) {
        this.resultService.deleteAllResultsByPlayer(playerId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Results deleted");
    }

    @PutMapping(value = "/results/{id}")
    public @ResponseBody Result updateResult(@RequestBody Result newResult, @PathVariable Long id) {
        Optional<Result> rOptional = this.resultService.findResult(id);
        if (!rOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found");
        try {
            Result result = rOptional.get();
            result.setData(newResult.getData());
            result.setTotalPoints(newResult.getTotalPoints());
            return this.resultService.saveResult(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
