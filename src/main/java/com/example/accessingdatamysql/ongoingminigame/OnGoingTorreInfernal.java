package com.example.accessingdatamysql.ongoingminigame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnGoingTorreInfernal{

    private List<Player> players;
    private List<Card> remainingCards;
    private Map<Long, Card> playerCard;
    private Map<Long, Integer> points;
    private Card currentCard;

    public OnGoingTorreInfernal(Long gameId, Game game, Iterable<Card> cards){ 
        this.players = List.copyOf(game.getPlayers());
        this.remainingCards = new ArrayList<Card>();
        cards.forEach(card->remainingCards.add(card));
        this.points = new HashMap<Long, Integer>();
        this.playerCard = new HashMap<Long, Card>();
        for(Player player: this.players){
            points.put(player.getId(), 0);
            Random random = new Random();
            int randomindex = random.ints(0, remainingCards.size()).findFirst().getAsInt();
            Card card = remainingCards.get(randomindex);
            remainingCards.remove(card);
            playerCard.put(player.getId(), card);
        }
        Random random2 = new Random();
        int randomindex2 = random2.ints(0, remainingCards.size()).findFirst().getAsInt();
        this.currentCard = remainingCards.get(randomindex2);
        remainingCards.remove(currentCard);
    }

    @JsonInclude
    public int getRemainingSize(){
        return remainingCards.size();
    }
}