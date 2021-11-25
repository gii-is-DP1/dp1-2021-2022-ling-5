package com.example.accessingdatamysql.ongoingRegaloEnvenenado;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class OnGoingRegaloEnvenenadoService {
    private static OnGoingRegaloEnvenenadoRepository ongoing = null;

    public OnGoingRegaloEnvenenadoService() {
        if (ongoing == null) {
            ongoing = new OnGoingRegaloEnvenenadoRepository();
        }
    }

    public Iterable<OnGoingRegaloEnvenenado> getAll() {
        return ongoing.getAllOnGoingGames();
    }

    public void createGame(Long gameId, Game game, Iterable<Card> cards) {
        ongoing.createGame(gameId, game, cards);
    }

    public void newCenterCard(Long gameId) {
        ongoing.newCenterCard(gameId);
    }

    public Card getPlayerCard(Long gameId, Long playerId) {
        return ongoing.getPlayerCard(gameId, playerId);
    }

    public Card getCenterCard(Long gameId) {
        return ongoing.getCenterCard(gameId);
    }

    public int getPoints(Long gameId, Long playerId) {
        return ongoing.getPoints(gameId, playerId);
    }

    public void addPoints(Long gameId, Long playerId, Integer points) {
        ongoing.addPoints(gameId, playerId, points);
    }

    public OnGoingRegaloEnvenenado getGame(Long gameId) {
        return ongoing.getGame(gameId);
    }

    public void deleteGame(Long gameId) {
        ongoing.deleteGame(gameId);
    }
}
