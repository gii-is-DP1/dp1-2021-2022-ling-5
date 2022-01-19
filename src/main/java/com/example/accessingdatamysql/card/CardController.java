package com.example.accessingdatamysql.card;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/api")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private FigureService figureService;

    @PostMapping(value = "/cards") // Map ONLY POST Requests
    public @ResponseBody Card addNewCard(@RequestBody Card card) {
        card.setFigures(new ArrayList<Figure>());
        try {
            return this.cardService.saveCard(card);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/cards/{cardId}/figures/{figureId}")
    public @ResponseBody Card addNewFigureToCard(@PathVariable Long cardId, @PathVariable Long figureId) {
        Optional<Card> card = this.cardService.findCard(cardId);
        Optional<Figure> figure = this.figureService.findFigure(figureId);
        if (!card.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
        }
        if (figure.isPresent()) {

            try {
                if (figure.get().getCards() == null)
                    figure.get().setCards(new ArrayList<Card>());
                if (card.get().getFigures() == null)
                    card.get().setFigures(new ArrayList<Figure>());

                if (!figure.get().getCards().contains(card.get()))
                    figure.get().getCards().add(card.get());
                if (!card.get().getFigures().contains(figure.get()))
                    card.get().getFigures().add(figure.get());

                return this.cardService.saveCard(card.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
    }

    @GetMapping(value = "/cards")
    public @ResponseBody Iterable<Card> getAllCards() {
        return this.cardService.findAllCards();
    }

    @GetMapping(value = "/cards/{id}")
    public @ResponseBody Card getCardById(@PathVariable Long id) {
        Optional<Card> card = this.cardService.findCard(id);
        if (card.isPresent())
            return card.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
    }

    @DeleteMapping(value = "/cards/{id}")
    public @ResponseBody void deleteCard(@PathVariable Long id) {
        Optional<Card> card = this.cardService.findCard(id);
        if (card.isPresent()) {
            this.cardService.deleteCard(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Card deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
    }

    @DeleteMapping(value = "/cards")
    public @ResponseBody void deleteAllCards() {
        this.cardService.deleteAllCards();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Cards deleted");
    }

    @DeleteMapping(value = "/cards/{cardId}/figures/{figureId}")
    public @ResponseBody void deleteFigureFromCard(@PathVariable Long figureId, @PathVariable Long cardId) {
        Optional<Card> card = this.cardService.findCard(cardId);
        Optional<Figure> figure = this.figureService.findFigure(figureId);
        if (!card.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
        if (!figure.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");

        if (figure.get().getCards() == null) {
            figure.get().setCards(new ArrayList<Card>());
            if (card.get().getFigures() == null)
                card.get().setFigures(new ArrayList<Figure>());
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "This card has no figures");
        }

        card.get().getFigures().remove(figure.get());
        figure.get().getCards().remove(card.get());
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Figure deleted from card");
    }

    @PutMapping(value = "/cards/{id}")
    public @ResponseBody Card updateCard(@RequestBody Card newCard, @PathVariable Long id) {
        Optional<Card> optCard = this.cardService.findCard(id);
        if (!optCard.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
        }
        try {
            Card card = optCard.get();
            card.setName(newCard.getName());
            return this.cardService.saveCard(card);
        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
