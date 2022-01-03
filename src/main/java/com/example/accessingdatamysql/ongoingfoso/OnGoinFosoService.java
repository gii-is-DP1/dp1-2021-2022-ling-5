package com.example.accessingdatamysql.ongoingfoso;

import java.util.List;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class OnGoinFosoService {
    private static OnGoingFosoRepository ongoing = null;

    public OnGoinFosoService(){
        if(ongoing==null){
            ongoing = new OnGoingFosoRepository();
        }
    }

    public Iterable<OnGoingFoso> getAll(){
        return ongoing.getAllOnGoingGames();
    }

    public static void creatGame(Long gameId, Game game, Iterable<Card> cards){
        ongoing.creatGame(gameId, game, cards);
    }

    public Card getPlayerCard(Long gameId, Long playerId){
        return ongoing.getPlayerCard(gameId, playerId);
    }

    public Card getCenterCard(Long gameId){
        return ongoing.getCenterCard(gameId);
    }

    public int getPoints(Long gameId, Long playerId){
        return ongoing.getPoints(gameId, playerId);
    }

    public void changeCards(Long playerId,Long gameId){
        ongoing.changeCards(playerId,gameId);
    }

    public List<Long> getPositions(Long gameId){
        return ongoing.getPositions(gameId);
    }

    public void addPoints(Long gameId, Long playerId, Integer points){
        ongoing.addPoints(gameId, playerId, points);
    }

    public OnGoingFoso getGame(Long gameId){
        return ongoing.getGame(gameId);
    }

    public void deleteGame(Long gameId){
        ongoing.deleteGame(gameId);
    }
}
