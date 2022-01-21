package com.example.accessingdatamysql.card;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.transaction.annotation.Transactional;
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

  @Transactional(readOnly = true)
  public Optional<Card> findCard(Long id) throws DataAccessException {
    return cardRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Card> findAllCards() throws DataAccessException {
    return StreamSupport
        .stream(cardRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteCard(Long id) throws DataAccessException {
    cardRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllCards() throws DataAccessException {
    cardRepository.deleteAll();
  }
}
