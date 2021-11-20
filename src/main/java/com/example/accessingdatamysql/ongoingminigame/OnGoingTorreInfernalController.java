package com.example.accessingdatamysql.ongoingminigame;

import java.util.Map;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.card.CardService;
import com.example.accessingdatamysql.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class OnGoingTorreInfernalController {
    @Autowired
    private OnGoinTorreInfernalService onGoinTorreInfernalService;

    @Autowired
    private GameService gameService;

    @Autowired
    private CardService cardService;

    //curl -H "Content-Type: application/json" -X POST localhost:8080/api/ongoingTorreInfernal -d "{\"gameId\":1}"
    @PostMapping(value = "/ongoingTorreInfernal")
    public @ResponseBody OnGoingTorreInfernal createGame(@RequestBody Request request){
        onGoinTorreInfernalService.creatGame(request.getGameId(), 
            gameService.findGame(request.getGameId()).get(),
            cardService.findAllCards());
        return onGoinTorreInfernalService.getGame(request.getGameId());
    }

    @GetMapping(value = "/ongoingTorreInfernal")
    public @ResponseBody Map<Long, OnGoingTorreInfernal> getAll(){
        return onGoinTorreInfernalService.getAll();
    }

    @GetMapping(value = "/ongoingTorreInfernal/{gameId}")
    public @ResponseBody OnGoingTorreInfernal getGameById(@PathVariable Long gameId){
        return onGoinTorreInfernalService.getGame(gameId);
    }
    
    @GetMapping(value = "/players/{playerId}/ongoingTorreInfernal/{gameId}")
    public @ResponseBody Card getPlayerCard(@PathVariable Long playerId, @PathVariable Long gameId){
        return onGoinTorreInfernalService.getPlayerCard(gameId, playerId);
    }
}
