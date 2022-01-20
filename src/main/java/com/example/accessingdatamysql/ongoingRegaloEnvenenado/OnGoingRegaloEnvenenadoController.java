package com.example.accessingdatamysql.ongoingRegaloEnvenenado;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.card.CardService;
import com.example.accessingdatamysql.game.GameService;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@Controller
@RequestMapping("/api")
public class OnGoingRegaloEnvenenadoController {
    @Autowired
    private OnGoingRegaloEnvenenadoService onGoingRegaloEnvenenadoService;

    @Autowired
    private GameService gameService;

    @Autowired
    private CardService cardService;

    @PostMapping(value = "/ongoingRegaloEnvenenado")
    public @ResponseBody OnGoingRegaloEnvenenado createGame(@RequestBody Request request) {
        onGoingRegaloEnvenenadoService.createGame(request.getGameId(), gameService.findGame(request.getGameId()).get(),
                cardService.findAllCards());
        return onGoingRegaloEnvenenadoService.getGame(request.getGameId());
    }

    @GetMapping(value = "/ongoingRegaloEnvenenado")
    public @ResponseBody Iterable<OnGoingRegaloEnvenenado> getAll() {
        return onGoingRegaloEnvenenadoService.getAll();
    }

    @GetMapping(value = "/ongoingRegaloEnvenenado/{gameId}")
    public @ResponseBody OnGoingRegaloEnvenenado getGameById(@PathVariable Long gameId) {
        return onGoingRegaloEnvenenadoService.getGame(gameId);
    }

    @GetMapping(value = "/players/{playerId}/ongoingRegaloEnvenenado/{gameId}")
    public @ResponseBody Card getPlayerCard(@PathVariable Long playerId, @PathVariable Long gameId) {
        return onGoingRegaloEnvenenadoService.getPlayerCard(gameId, playerId);
    }

    @GetMapping(value = "/ongoingRegaloEnvenenado/{gameId}/card")
    public @ResponseBody Card getCenterCard(@PathVariable Long gameId) {
        return onGoingRegaloEnvenenadoService.getCenterCard(gameId);
    }

    @GetMapping(value = "/players/{playerId}/ongoingRegaloEnvenenado/{gameId}/points")
    public @ResponseBody Integer getPoints(@PathVariable Long playerId, @PathVariable Long gameId) {
        return onGoingRegaloEnvenenadoService.getPoints(gameId, playerId);
    }

    @PutMapping(value = "/ongoingRegaloEnvenenado/{gameId}/card")
    public @ResponseBody OnGoingRegaloEnvenenado newCenterCard(@RequestBody RequestNewCard request,
            @PathVariable Long gameId) throws BadRequest {
        try{
            onGoingRegaloEnvenenadoService.newCenterCard(gameId, request);
            return onGoingRegaloEnvenenadoService.getGame(gameId);
        } catch(Error e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/players/{playerId}/ongoingRegaloEnvenenado/{gameId}/points")
    public @ResponseBody OnGoingRegaloEnvenenado addPoints(@RequestBody RequestAddPoints request,
            @PathVariable Long playerId, @PathVariable Long gameId) {
        onGoingRegaloEnvenenadoService.addPoints(gameId, playerId, request.getPoints());
        return onGoingRegaloEnvenenadoService.getGame(gameId);
    }

    @DeleteMapping(value = "/ongoingRegaloEnvenenado/{gameId}")
    public @ResponseBody String deleteGame(@PathVariable Long gameId) {
        OnGoingRegaloEnvenenado onGoingRegaloEnvenenado = getGameById(gameId);
        if (onGoingRegaloEnvenenado != null) {
            onGoingRegaloEnvenenadoService.deleteGame(gameId);
            return "Game Deleted";
        } else {
            return "No on going game found";
        }
    }
}
