package com.example.accessingdatamysql.ongoingfoso;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameRepository;
import com.example.accessingdatamysql.game.State;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class OnGoingFosoService {

  @Autowired
  private GameRepository gameRepository;

  private static OnGoingFosoRepository ongoing = null;

  public OnGoingFosoService() {
    if (ongoing == null) {
      ongoing = new OnGoingFosoRepository();
    }
  }

  public Iterable<OnGoingFoso> getAll() {
    return ongoing.getAllOnGoingGames();
  }

  @Transactional
  public static void createGame(Long gameId, Game game, Iterable<Card> cards) {
    ongoing.createGame(gameId, game, cards);
  }

  @Transactional(readOnly = true)
  public Card getPlayerCard(Long gameId, Long playerId) {
    return ongoing.getPlayerCard(gameId, playerId);
  }

  @Transactional
  public Card getCenterCard(Long gameId) {
    return ongoing.getCenterCard(gameId);
  }

  @Transactional(readOnly = true)
  public int getPoints(Long gameId, Long playerId) {
    return ongoing.getPoints(gameId, playerId);
  }

  @Transactional
  public void changeCards(Long playerId, Long gameId) {
    ongoing.changeCards(playerId, gameId);
  }

  @Transactional(readOnly = true)
  public List<Long> getPositions(Long gameId) {
    return ongoing.getPositions(gameId);
  }

  @Transactional
  public void addPoints(Long gameId, Long playerId, Integer points) {
    ongoing.addPoints(gameId, playerId, points);
  }

  @Transactional(readOnly = true)
  public OnGoingFoso getGame(Long gameId) {
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
