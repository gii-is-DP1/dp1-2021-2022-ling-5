package com.example.accessingdatamysql.ongoingfoso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.user.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnGoingFoso{

    private List<Player> players;
    private List<Card> remainingCards;
    private Map<Long, Card> playerCard;
    private Map<Long, Integer> points;
    private Card currentCard;
    private Integer limit;
    private List<Long> orden;

    public OnGoingFoso(Long gameId, Game game, Iterable<Card> cards){ 
        this.orden = new ArrayList<>();
        this.players = List.copyOf(game.getPlayers());
        this.remainingCards = new ArrayList<Card>();
        cards.forEach(card->remainingCards.add(card));
        this.points = new HashMap<Long, Integer>();
        this.playerCard = new HashMap<Long, Card>();
        this.limit=remainingCards.size()/players.size() -1;
        for(Player player: this.players){
            points.put(player.getId(), 0);
            Random random = new Random();
            int randomindex = random.ints(0, remainingCards.size()-1).findFirst().getAsInt();
            Card card = remainingCards.get(randomindex);
            remainingCards.remove(card);
            playerCard.put(player.getId(), card);
        }
        Random random2 = new Random();
        int randomindex2 = random2.ints(0, remainingCards.size()-1).findFirst().getAsInt();
        this.currentCard = remainingCards.get(randomindex2);
        remainingCards.remove(currentCard);
    }
}