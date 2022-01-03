package com.example.accessingdatamysql.ongoingminigame;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.card.CardService;
import com.example.accessingdatamysql.game.GameService;
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
@RequestMapping("/api")
public class OnGoingTorreInfernalController {

  @Autowired
  private OnGoingTorreInfernalService onGoinTorreInfernalService;

  @Autowired
  private GameService gameService;

  @Autowired
  private CardService cardService;

  // curl -H "Content-Type: application/json" -X POST
  // localhost:8080/api/ongoingTorreInfernal -d "{\"gameId\":1}"
  @PostMapping(value = "/ongoingTorreInfernal")
  public @ResponseBody OnGoingTorreInfernal createGame(
    @RequestBody Request request
  ) {
    onGoinTorreInfernalService.createGame(
      request.getGameId(),
      gameService.findGame(request.getGameId()).get(),
      cardService.findAllCards()
    );
    return onGoinTorreInfernalService.getGame(request.getGameId());
  }

  @GetMapping(value = "/ongoingTorreInfernal")
  public @ResponseBody Iterable<OnGoingTorreInfernal> getAll() {
    return onGoinTorreInfernalService.getAll();
  }

  @GetMapping(value = "/ongoingTorreInfernal/{gameId}")
  public @ResponseBody OnGoingTorreInfernal getGameById(
    @PathVariable Long gameId
  ) {
    return onGoinTorreInfernalService.getGame(gameId);
  }

  @GetMapping(value = "/players/{playerId}/ongoingTorreInfernal/{gameId}")
  public @ResponseBody Card getPlayerCard(
    @PathVariable Long playerId,
    @PathVariable Long gameId
  ) {
    return onGoinTorreInfernalService.getPlayerCard(gameId, playerId);
  }

  @GetMapping(value = "/ongoingTorreInfernal/{gameId}/card")
  public @ResponseBody Card getCenterCard(@PathVariable Long gameId) {
    return onGoinTorreInfernalService.getCenterCard(gameId);
  }

  @GetMapping(
    value = "/players/{playerId}/ongoingTorreInfernal/{gameId}/points"
  )
  public @ResponseBody Integer getPoints(
    @PathVariable Long playerId,
    @PathVariable Long gameId
  ) {
    return onGoinTorreInfernalService.getPoints(gameId, playerId);
  }

  @PutMapping(value = "/ongoingTorreInfernal/{gameId}/card")
  public @ResponseBody OnGoingTorreInfernal newCenterCard(
    @RequestBody RequestNewCard request,
    @PathVariable Long gameId
  ) {
    onGoinTorreInfernalService.newCenterCard(gameId, request);
    return onGoinTorreInfernalService.getGame(gameId);
  }

  @PutMapping(
    value = "/players/{playerId}/ongoingTorreInfernal/{gameId}/points"
  )
  public @ResponseBody OnGoingTorreInfernal addPoints(
    @RequestBody RequestAddPoints request,
    @PathVariable Long playerId,
    @PathVariable Long gameId
  ) {
    onGoinTorreInfernalService.addPoints(gameId, playerId, request.getPoints());
    return onGoinTorreInfernalService.getGame(gameId);
  }

  @DeleteMapping(value = "/ongoingTorreInfernal/{gameId}")
  public @ResponseBody String deleteGame(@PathVariable Long gameId) {
    OnGoingTorreInfernal onGoingTorreInfernal = getGameById(gameId);
    if (onGoingTorreInfernal != null) {
      onGoinTorreInfernalService.deleteGame(gameId);
      return "Game Deleted";
    } else {
      return "No on going game found";
    }
  }
}
