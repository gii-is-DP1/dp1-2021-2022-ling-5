package com.example.accessingdatamysql.ongoingminigame;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.card.CardService;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameRepository;
import com.example.accessingdatamysql.game.GameService;
import com.example.accessingdatamysql.game.State;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.result.ResultService;
import com.example.accessingdatamysql.user.PlayerService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class OnGoingTorreInfernalService {

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private ResultService resultService;

  @Autowired
  private PlayerService playerService;

  @Autowired
  private GameService gameService;

  @Autowired
  private CardService cardService;

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
  public void createGame(Long gameId) {
    Game game = gameService.findGame(gameId).get();
    Iterable<Card> cards = cardService.findAllCards();
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
  public OnGoingTorreInfernal getGame(Long gameId) {
    return ongoing.getGame(gameId);
  }

  @Transactional
  public void deleteGame(Long gameId) {
    Game game = gameRepository.findById(gameId).get();
    Map<Long, Integer> result = this.getGame(gameId).getPoints();
    Long winner=0L;
    Integer points=0;
    for(Entry<Long, Integer> e: result.entrySet()){
      if(points<e.getValue()){
          points=e.getValue();
        winner=e.getKey();
      }
    }

    if(result.values().stream().filter(p->p==result.values().stream().max((x,y)->x.compareTo(y)).get()).count()>1L){
      winner=null;
    }else{
      result.put(winner, result.get(winner)+5);
    }

    for(Entry<Long, Integer> e: result.entrySet()){
      Result res = new Result();
      res.setData(""+e.getValue());
      res.setGame(game);
      res.setPlayer(playerService.findPlayer(e.getKey()).get());
      res.setTotalPoints(e.getValue());
      resultService.saveResult(res);
    }
    game.setWinner(winner);
    game.setEndTime(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    game.setState(State.FINISHED);
    ongoing.deleteGame(gameId);
  }
}
