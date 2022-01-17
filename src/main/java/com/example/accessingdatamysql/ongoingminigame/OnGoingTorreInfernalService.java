package com.example.accessingdatamysql.ongoingminigame;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class OnGoingTorreInfernalService {

  private static OnGoingTorreInfernalRepository ongoing = null;

  public OnGoingTorreInfernalService() {
    if (ongoing == null) {
      ongoing = new OnGoingTorreInfernalRepository();
    }
  }

  public Iterable<OnGoingTorreInfernal> getAll() {
    return ongoing.getAllOnGoingGames();
  }

  @Transactional
  public void createGame(Long gameId, Game game, Iterable<Card> cards) {
    ongoing.createGame(gameId, game, cards);
  }

  @Transactional
  public void newCenterCard(Long gameId, RequestNewCard request) {
    ongoing.newCenterCard(gameId, request);
  }

  @Transactional(readOnly = true)
  public Card getPlayerCard(Long gameId, Long playerId) {
    return ongoing.getPlayerCard(gameId, playerId);
  }

  @Transactional(readOnly = true)
  public Card getCenterCard(Long gameId) {
    return ongoing.getCenterCard(gameId);
  }

  @Transactional(readOnly = true)
  public int getPoints(Long gameId, Long playerId) {
    return ongoing.getPoints(gameId, playerId);
  }

  @Transactional
  public void addPoints(Long gameId, Long playerId, Integer points) {
    ongoing.addPoints(gameId, playerId, points);
  }

  @Transactional(readOnly = true)
  public OnGoingTorreInfernal getGame(Long gameId) {
    return ongoing.getGame(gameId);
  }

  @Transactional
  public void deleteGame(Long gameId) {
    ongoing.deleteGame(gameId);
  }
}
