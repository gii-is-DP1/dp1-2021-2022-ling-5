package com.example.accessingdatamysql.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardServiceTests {
    @Autowired
    protected CardService cardService;

    @Test
    @Transactional
    public void shouldInsertCard() {
        List<Card> cards = this.cardService.findAllCards();
        int found = cards.size();

        Card card = new Card("c01");
        this.cardService.saveCard(card);
        assertNotEquals(card.getId(), 0L);

        cards = this.cardService.findAllCards();
        assertEquals(cards.size(), found + 1);
    }

    @Test
    @Transactional(readOnly = true)
    public void shouldFindSingleCard() {
        Optional<Card> cardOpt = this.cardService.findCard(1L);
        if (cardOpt.isPresent()) {
            Card card = cardOpt.get();
            assertEquals(card.getName(), "c01");
        }
    }

    @Test
    @Transactional
    void shouldUpdateCard() {
        Optional<Card> card = this.cardService.findCard(1L);
        if (card.isPresent()) {
            String oldName = card.get().getName();
            String newName = oldName + "X";

            card.get().setName(newName);
            this.cardService.saveCard(card.get());

            card = this.cardService.findCard(1L);
            if (card.isPresent()) {
                assertEquals(card.get().getName(), newName);
            }
        }
    }

    @Test
    @Transactional
    void shouldDeleteCard() {
        Card card = new Card("c03");
        card = this.cardService.saveCard(card);
        List<Card> cards = this.cardService.findAllCards();
        int found = cards.size();

        this.cardService.deleteCard(card.getId());
        cards = this.cardService.findAllCards();
        assertEquals(cards.size(), found - 1);

    }

}
