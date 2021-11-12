package com.example.accessingdatamysql.card;

import java.util.Optional;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;

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
@RequestMapping(value = "/api")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private FigureService figureService;

    @PostMapping(value = "/cards") // Map ONLY POST Requests
    public @ResponseBody Card addNewCard(@RequestBody Card card) {
        return this.cardService.saveCard(card);
    }

    @PostMapping(value = "/cards/{cardId}/figures/{figureId}")
    public @ResponseBody Card addNewFigureToCard(@PathVariable Long cardId, @PathVariable Long figureId) {
        Optional<Card> card = this.cardService.findCard(cardId);
        Optional<Figure> figure = this.figureService.findFigure(figureId);
        if (!card.isPresent())
            return null;
        if (figure.isPresent()) {
            figure.get().getCards().add(card.get());
            card.get().getFigures().add(figure.get());
            this.cardService.saveCard(card.get());
        }
        return card.get();
    }

    @GetMapping(value = "/cards")
    public @ResponseBody Iterable<Card> getAllCards() {
        return this.cardService.findAllCards();
    }

    @GetMapping(value = "/cards/{id}")
    public @ResponseBody Optional<Card> getCardById(@PathVariable Long id) {
        return this.cardService.findCard(id);
    }

    @DeleteMapping(value = "/cards/{id}")
    public @ResponseBody String deleteCard(@PathVariable Long id) {
        this.cardService.deleteCard(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/cards")
    public @ResponseBody String deleteAllCards() {
        this.cardService.deleteAllCards();
        return "Deleted all";
    }

    @DeleteMapping(value = "/cards/{cardId}/figures/{figureId}")
    public @ResponseBody String deleteFigureFromCard(@PathVariable Long figureId, @PathVariable Long cardId) {
        Optional<Card> card = this.cardService.findCard(cardId);
        Optional<Figure> figure = this.figureService.findFigure(figureId);
        if (!card.isPresent())
            return "Card not found";
        else if (!figure.isPresent())
            return "Figure not found";
        else {
            card.get().getFigures().remove(figure.get());
            figure.get().getCards().remove(card.get());
            return "Figure deleted from card";
        }
    }

    @PutMapping(value = "/cards/{id}")
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
