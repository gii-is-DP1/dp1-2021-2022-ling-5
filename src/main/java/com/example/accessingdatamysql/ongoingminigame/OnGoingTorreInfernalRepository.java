package com.example.accessingdatamysql.ongoingminigame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;

public class OnGoingTorreInfernalRepository {
    private static Map<Long, OnGoingTorreInfernal> ongoinggames=null;

    public OnGoingTorreInfernalRepository(){
        if(ongoinggames==null){
            ongoinggames = new HashMap<Long, OnGoingTorreInfernal>();
        }
    }

    public void creatGame(Long gameId, Game game, Iterable<Card> cards){
        OnGoingTorreInfernal onGoingTorreInfernal = new OnGoingTorreInfernal(gameId, game, cards);
        ongoinggames.put(gameId, onGoingTorreInfernal);
    }

    public void newCenterCard(Long gameId){
        OnGoingTorreInfernal onGoingTorreInfernal = ongoinggames.get(gameId);
        if(onGoingTorreInfernal.getRemainingCards().size()!=0){
            Random random = new Random();
            int randomindex = random.ints(0, onGoingTorreInfernal.getRemainingCards().size()).findFirst().getAsInt();
            Card newCard = onGoingTorreInfernal.getRemainingCards().get(randomindex);
            onGoingTorreInfernal.getRemainingCards().remove(newCard);
            onGoingTorreInfernal.setCurrentCard(newCard);
        } else{
            onGoingTorreInfernal.setCurrentCard(null);
        }
        
    }

    public Card getPlayerCard(Long gameId, Long playerId){
        return ongoinggames.get(gameId).getPlayerCard().get(playerId);
    }

    public Card getCenterCard(Long gameId){
        return ongoinggames.get(gameId).getCurrentCard();
    }

    public int getPoints(Long gameId, Long playerId){
        return ongoinggames.get(gameId).getPoints().get(playerId);
    }

    public void addPoints(Long gameId, Long playerId, Integer points){
        ongoinggames.get(gameId).getPoints().put(playerId, 
            ongoinggames.get(gameId).getPoints().get(playerId) + points);
    }

    public OnGoingTorreInfernal getGame(Long gameId){
        return ongoinggames.get(gameId);
    }

    public Iterable<OnGoingTorreInfernal> getAllOnGoingGames(){
        List<OnGoingTorreInfernal> ongoing = new ArrayList<OnGoingTorreInfernal>();
        for(Entry<Long, OnGoingTorreInfernal> e: ongoinggames.entrySet()){
            ongoing.add(e.getValue());
        }
        return ongoing;
    }
    public void deleteGame(Long gameId){
        ongoinggames.remove(gameId);
    }
}
