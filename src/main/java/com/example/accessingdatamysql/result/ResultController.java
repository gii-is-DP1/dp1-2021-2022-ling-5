package com.example.accessingdatamysql.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameService;
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
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private GameService gameService;

    @PostMapping(value = "/games/{gameId}/results") // Map ONLY POST Requests
    public @ResponseBody Result addNewResult(@RequestBody Result result, @PathVariable Long gameId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        if (game.isPresent()) {
            result.setGame(game.get());
        }
        return this.resultService.saveResult(result);
    }

    @GetMapping(value = "/results")
    public @ResponseBody Iterable<Result> getAllResults() {
        return this.resultService.findAllResults();
    }

    @GetMapping(value = "/results/{id}")
    public @ResponseBody Optional<Result> getResultById(@PathVariable Long id) {
        return this.resultService.findResult(id);
    }

    @GetMapping(value = "/games/{gameId}/results")
    public @ResponseBody List<Result> getAllResultsByGame(@PathVariable Long gameId) {
        Optional<Game> game = this.gameService.findGame(gameId);
        if (game.isPresent()) {
            return this.resultService.findAllResultsByGame(gameId);
        } else {
            return new ArrayList<Result>();
        }
    }

    @GetMapping(value = "/players/{playerId}/results")
    public @ResponseBody List<Result> getAllResultsByPlayer(@PathVariable Long playerId) {
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (player.isPresent()) {
            return this.resultService.findAllResultsByPlayer(playerId);
        } else {
            return new ArrayList<Result>();
        }
    }

    @DeleteMapping(value = "/results/{id}")
    public @ResponseBody String deleteResult(@PathVariable Long id) {
        this.resultService.deleteResult(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/results")
    public @ResponseBody String deleteAllResults() {
        this.resultService.deleteAllResults();
        return "Deleted all";
    }

    @DeleteMapping(value = "/games/{gameId}/results")
    public @ResponseBody String deleteAllResultsByGame(@PathVariable Long gameId) {
        this.resultService.deleteAllResultsByGame(gameId);
        return "Deleted all";
    }

    @DeleteMapping(value = "/players/{playerId}/results")
    public @ResponseBody String deleteAllResultsByPlayer(@PathVariable Long playerId) {
        this.resultService.deleteAllResultsByPlayer(playerId);
        return "Deleted all";
    }

    @PutMapping(value = "/results/{id}")
    public @ResponseBody Result updateResult(@RequestBody Result newResult, @PathVariable Long id) {
        this.resultService.findResult(id).map(result -> {
            result.setData(newResult.getData());
            return this.resultService.saveResult(result);
        }).orElseGet(() -> {
            newResult.setId(id);
            return this.resultService.saveResult(newResult);
        });
        return newResult;
    }
}
