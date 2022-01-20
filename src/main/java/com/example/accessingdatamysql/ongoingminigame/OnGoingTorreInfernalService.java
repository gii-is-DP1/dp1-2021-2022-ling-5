package com.example.accessingdatamysql.ongoingminigame;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameRepository;
import com.example.accessingdatamysql.game.State;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class OnGoingTorreInfernalService {

  @Autowired
  private GameRepository gameRepository;

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
    Game game = gameRepository.findById(gameId).get();
    game.setEndTime(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    game.setState(State.FINISHED);
    ongoing.deleteGame(gameId);
  }
}
