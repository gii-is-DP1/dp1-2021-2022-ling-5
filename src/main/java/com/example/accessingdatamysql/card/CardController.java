package com.example.accessingdatamysql.card;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping(path = "/cards") // Map ONLY POST Requests
    public @ResponseBody Card addNewCard(@RequestBody Card card) {
        return this.cardService.saveCard(card);
    }

    @GetMapping(path = "/cards")
    public @ResponseBody Iterable<Card> getAllCards() {
        return this.cardService.findAllCards();
    }

    @GetMapping(path = "/cards/{id}")
    public @ResponseBody Optional<Card> getCardById(@PathVariable Long id) {
        return this.cardService.findCard(id);
    }

    @DeleteMapping(path = "/cards/{id}")
    public @ResponseBody String deleteCard(@PathVariable Long id) {
        this.cardService.deleteCard(id);
        return "Deleted";
    }

    @DeleteMapping(path = "/cards")
    public @ResponseBody String deleteAllCards() {
        this.cardService.deleteAllCards();
        return "Deleted all";
    }

    @PutMapping("/cards/{id}")
    public @ResponseBody Card updateCard(@RequestBody Card newCard, @PathVariable Long id) {
        this.cardService.findCard(id).map(card -> {
            card.setImage(newCard.getImage());
            return this.cardService.saveCard(card);
        }).orElseGet(() -> {
            newCard.setId(id);
            return this.cardService.saveCard(newCard);
        });
        return newCard;
    }
}
