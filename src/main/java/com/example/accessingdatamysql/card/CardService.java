package com.example.accessingdatamysql.card;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        return cardRepository.save(card);
    }

    public Optional<Card> findCard(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> findAllCards() {
        return StreamSupport.stream(cardRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    public void deleteAllCards() {
        cardRepository.deleteAll();
    }
}
