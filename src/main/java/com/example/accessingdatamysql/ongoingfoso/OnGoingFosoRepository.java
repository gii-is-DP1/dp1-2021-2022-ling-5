package com.example.accessingdatamysql.ongoingfoso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;

public class OnGoingFosoRepository {
    private static Map<Long, OnGoingFoso> ongoinggames=null;

    public OnGoingFosoRepository(){
        if(ongoinggames==null){
            ongoinggames = new HashMap<Long, OnGoingFoso>();
        }
    }

    public void creatGame(Long gameId, Game game, Iterable<Card> cards){
        OnGoingFoso onGoingFoso = new OnGoingFoso(gameId, game, cards);
        ongoinggames.put(gameId, onGoingFoso);
    }


    public void changeCards(Long playerId, Long gameId){
        OnGoingFoso onGoingFoso = ongoinggames.get(gameId);
        Card newCard=ongoinggames.get(gameId).getPlayerCard().get(playerId);
        onGoingFoso.setCurrentCard(newCard);
        if(ongoinggames.get(gameId).getPoints().get(playerId)<ongoinggames.get(gameId).getLimit()){
            Random random = new Random();
            int randomindex = random.ints(0, onGoingFoso.getRemainingCards().size()).findFirst().getAsInt();
            Card newPlayerCard = onGoingFoso.getRemainingCards().get(randomindex);
            onGoingFoso.getRemainingCards().remove(newPlayerCard);
            onGoingFoso.getPlayerCard().put(playerId, newPlayerCard);
        }else{
            onGoingFoso.getPlayerCard().put(playerId, null);
            onGoingFoso.getOrden().add(playerId);
            if(onGoingFoso.getOrden().size()==onGoingFoso.getPlayers().size()-1){
                for(Integer i=0;i<onGoingFoso.getPlayers().size();i++){
                    if(!onGoingFoso.getOrden().contains(onGoingFoso.getPlayers().get(i).getId())){
                        Long idFinal=onGoingFoso.getPlayers().get(i).getId();
                        onGoingFoso.getOrden().add(idFinal);
                    }
                }
            }
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

    public OnGoingFoso getGame(Long gameId){
        return ongoinggames.get(gameId);
    }

    public Iterable<OnGoingFoso> getAllOnGoingGames(){
        List<OnGoingFoso> ongoing = new ArrayList<OnGoingFoso>();
        for(Entry<Long, OnGoingFoso> e: ongoinggames.entrySet()){
            ongoing.add(e.getValue());
        }
        return ongoing;
    }
    public void deleteGame(Long gameId){
        ongoinggames.remove(gameId);
    }

    public List<Long> getPositions(Long gameId){
        return ongoinggames.get(gameId).getOrden();
    }
}
