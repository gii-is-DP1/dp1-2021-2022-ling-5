package com.example.accessingdatamysql.ongoingRegaloEnvenenado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;

public class OnGoingRegaloEnvenenadoRepository {
    private static Map<Long, OnGoingRegaloEnvenenado> ongoinggames = null;

    public OnGoingRegaloEnvenenadoRepository() {
        if (ongoinggames == null) {
            ongoinggames = new HashMap<Long, OnGoingRegaloEnvenenado>();
        }
    }

    public void createGame(Long gameId, Game game, Iterable<Card> cards) {
        OnGoingRegaloEnvenenado onGoingRegaloEnvenenado = new OnGoingRegaloEnvenenado(gameId, game, cards);
        ongoinggames.put(gameId, onGoingRegaloEnvenenado);
    }

    public void newCenterCard(Long gameId, Long playerId) {
        OnGoingRegaloEnvenenado onGoingRegaloEnvenenado = ongoinggames.get(gameId);
        Card currentCard = onGoingRegaloEnvenenado.getCurrentCard();
        onGoingRegaloEnvenenado.getPlayerCard().put(playerId, currentCard);
        System.out.println(onGoingRegaloEnvenenado.getPlayerCard());
        if (onGoingRegaloEnvenenado.getRemainingCards().size() != 0) {
            Random random = new Random();
            int randomindex = random.ints(0, onGoingRegaloEnvenenado.getRemainingCards().size()).findFirst().getAsInt();
            Card newCard = onGoingRegaloEnvenenado.getRemainingCards().get(randomindex);
            onGoingRegaloEnvenenado.getRemainingCards().remove(newCard);
            onGoingRegaloEnvenenado.setCurrentCard(newCard);
        } else {
            onGoingRegaloEnvenenado.setCurrentCard(null);
        }

    }

    public Card getPlayerCard(Long gameId, Long playerId) {
        return ongoinggames.get(gameId).getPlayerCard().get(playerId);
    }

    public Card getCenterCard(Long gameId) {
        return ongoinggames.get(gameId).getCurrentCard();
    }

    public int getPoints(Long gameId, Long playerId) {
        return ongoinggames.get(gameId).getPoints().get(playerId);
    }

    public void addPoints(Long gameId, Long playerId, Integer points) {
        ongoinggames.get(gameId).getPoints().put(playerId, ongoinggames.get(gameId).getPoints().get(playerId) + points);
    }

    public OnGoingRegaloEnvenenado getGame(Long gameId) {
        return ongoinggames.get(gameId);
    }

    public Iterable<OnGoingRegaloEnvenenado> getAllOnGoingGames() {
        List<OnGoingRegaloEnvenenado> ongoing = new ArrayList<OnGoingRegaloEnvenenado>();
        for (Entry<Long, OnGoingRegaloEnvenenado> e : ongoinggames.entrySet()) {
            ongoing.add(e.getValue());
        }
        return ongoing;
    }

    public void deleteGame(Long gameId) {
        ongoinggames.remove(gameId);
    }
}
