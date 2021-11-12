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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class CardController {
    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/cards", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Card addNewCard(@RequestBody Card card) {
        return this.cardService.saveCard(card);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public @ResponseBody Iterable<Card> getAllCards() {
        return this.cardService.findAllCards();
    }

    @RequestMapping(value = "/cards/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Card> getCardById(@PathVariable Long id) {
        return this.cardService.findCard(id);
    }

    @RequestMapping(value = "/cards/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteCard(@PathVariable Long id) {
        this.cardService.deleteCard(id);
        return "Deleted";
    }

    @RequestMapping(value = "/cards", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllCards() {
        this.cardService.deleteAllCards();
        return "Deleted all";
    }

    @RequestMapping(value = "/cards/{id}", method = RequestMethod.PUT)
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
