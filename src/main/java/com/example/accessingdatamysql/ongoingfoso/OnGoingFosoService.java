package com.example.accessingdatamysql.ongoingfoso;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class OnGoingFosoService {

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

  public Card getPlayerCard(Long gameId, Long playerId) {
    return ongoing.getPlayerCard(gameId, playerId);
  }

  @Transactional
  public Card getCenterCard(Long gameId) {
    return ongoing.getCenterCard(gameId);
  }

  public int getPoints(Long gameId, Long playerId) {
    return ongoing.getPoints(gameId, playerId);
  }

  @Transactional
  public void changeCards(Long playerId, Long gameId) {
    ongoing.changeCards(playerId, gameId);
  }

  public List<Long> getPositions(Long gameId) {
    return ongoing.getPositions(gameId);
  }

  @Transactional
  public void addPoints(Long gameId, Long playerId, Integer points) {
    ongoing.addPoints(gameId, playerId, points);
  }

  public OnGoingFoso getGame(Long gameId) {
    return ongoing.getGame(gameId);
  }

  @Transactional
  public void deleteGame(Long gameId) {
    ongoing.deleteGame(gameId);
  }
}
