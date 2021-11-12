package com.example.accessingdatamysql.card;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public Card saveCard(Card card) throws DataAccessException {
        cardRepository.save(card);
        return card;
    }

    public Optional<Card> findCard(Long id) {
        return cardRepository.findById(id);
    }

    public Iterable<Card> findAllCards() {
        return cardRepository.findAll();
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    public void deleteAllCards() {
        cardRepository.deleteAll();
    }
}
