package com.example.accessingdatamysql.ongoingfoso;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.card.CardService;
import com.example.accessingdatamysql.game.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class OnGoingFosoController {

  @Autowired
  private OnGoingFosoService onGoingFosoService;

  @Autowired
  private GameService gameService;

  @Autowired
  private CardService cardService;

  // curl -H "Content-Type: application/json" -X POST
  // localhost:8080/api/ongoingFoso -d "{\"gameId\":1}"
  @PostMapping(value = "/ongoingFoso")
  public @ResponseBody OnGoingFoso createGame(@RequestBody Request request) {
    onGoingFosoService.createGame(
      request.getGameId(),
      gameService.findGame(request.getGameId()).get(),
      cardService.findAllCards()
    );
    return onGoingFosoService.getGame(request.getGameId());
  }

  @GetMapping(value = "/ongoingFoso")
  public @ResponseBody Iterable<OnGoingFoso> getAll() {
    return onGoingFosoService.getAll();
  }

  @GetMapping(value = "/ongoingFoso/{gameId}")
  public @ResponseBody OnGoingFoso getGameById(@PathVariable Long gameId) {
    return onGoingFosoService.getGame(gameId);
  }

  @GetMapping(value = "/players/{playerId}/ongoingFoso/{gameId}")
  public @ResponseBody Card getPlayerCard(
    @PathVariable Long playerId,
    @PathVariable Long gameId
  ) {
    return onGoingFosoService.getPlayerCard(gameId, playerId);
  }

  @GetMapping(value = "/ongoingFoso/{gameId}/card")
  public @ResponseBody Card getCenterCard(@PathVariable Long gameId) {
    return onGoingFosoService.getCenterCard(gameId);
  }

  @GetMapping(value = "/players/{playerId}/ongoingFoso/{gameId}/points")
  public @ResponseBody Integer getPoints(
    @PathVariable Long playerId,
    @PathVariable Long gameId
  ) {
    return onGoingFosoService.getPoints(gameId, playerId);
  }

  @GetMapping(value = "/players/{playerId}/ongoingFoso/{gameId}/newcard")
  public @ResponseBody Card getNewCard(
    @PathVariable Long playerId,
    @PathVariable Long gameId
  ) {
    onGoingFosoService.addPoints(gameId, playerId, 1);
    onGoingFosoService.changeCards(playerId, gameId);
    return onGoingFosoService.getPlayerCard(gameId, playerId);
  }

  @GetMapping(value = "/ongoingFoso/{gameId}/positions")
  public @ResponseBody List<Long> getFinalPositions(@PathVariable Long gameId) {
    return onGoingFosoService.getPositions(gameId);
  }

  @DeleteMapping(value = "/ongoingFoso/{gameId}")
  public @ResponseBody String deleteGame(@PathVariable Long gameId) {
    OnGoingFoso onGoingTorreInfernal = getGameById(gameId);
    if (onGoingTorreInfernal != null) {
      onGoingFosoService.deleteGame(gameId);
      return "Game Deleted";
    } else {
      return "No on going game found";
    }
  }
}
