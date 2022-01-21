package com.example.accessingdatamysql.ongoingRegaloEnvenenado;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameRepository;
import com.example.accessingdatamysql.game.State;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.result.ResultService;
import com.example.accessingdatamysql.user.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class OnGoingRegaloEnvenenadoService {

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private ResultService resultService;

  @Autowired
  private PlayerService playerService;
  private static OnGoingRegaloEnvenenadoRepository ongoing = null;

  public OnGoingRegaloEnvenenadoService() {
    if (ongoing == null) {
      ongoing = new OnGoingRegaloEnvenenadoRepository();
    }
  }

  public Iterable<OnGoingRegaloEnvenenado> getAll() {
    return ongoing.getAllOnGoingGames();
  }

  @Transactional
  public void createGame(Long gameId, Game game, Iterable<Card> cards) {
    ongoing.createGame(gameId, game, cards);
  }

  @Transactional
  public void newCenterCard(Long gameId, Long playerId) {
    ongoing.newCenterCard(gameId, playerId);
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
  public OnGoingRegaloEnvenenado getGame(Long gameId) {
    return ongoing.getGame(gameId);
  }

  @Transactional
  public void deleteGame(Long gameId) {
    Game game = gameRepository.findById(gameId).get();
    Map<Long, Integer> result = this.getGame(gameId).getPoints();
    for (Entry<Long, Integer> e : result.entrySet()) {
      Result res = new Result();
      res.setData("0 " + "0 " + e.getValue());
      res.setGame(game);
      res.setPlayer(playerService.findPlayer(e.getKey()).get());
      res.setTotalPoints(e.getValue());
      resultService.saveResult(res);
    }
    game.setEndTime(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    game.setState(State.FINISHED);
    ongoing.deleteGame(gameId);
  }
}
