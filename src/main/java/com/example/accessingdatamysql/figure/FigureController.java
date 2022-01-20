package com.example.accessingdatamysql.figure;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.user.Admin;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.card.Card;

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
public class FigureController {
    @Autowired
    private FigureService figureService;

    @PostMapping(value = "/figures") 
    public @ResponseBody Figure addNewFigure(@RequestBody Figure figure) {
        try {
            figure.setAdmins(new ArrayList<Admin>());
            figure.setPlayers(new ArrayList<Player>());
            figure.setCards(new ArrayList<Card>());

            return this.figureService.saveFigure(figure);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping(value = "/figures")
    public @ResponseBody Iterable<Figure> getAllFigures() {
        return this.figureService.findAllFigures();
    }

    @GetMapping(value = "/figures/{id}")
    public @ResponseBody Figure getFigureById(@PathVariable Long id) {
        Optional<Figure> figure = this.figureService.findFigure(id);
        if (figure.isPresent())
            return figure.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
    }

    @DeleteMapping(value = "/figures/{id}")
    public @ResponseBody void deleteFigure(@PathVariable Long id) {
        Optional<Figure> figure = this.figureService.findFigure(id);
        if (figure.isPresent()) {
            this.figureService.deleteFigure(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Figure deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
    }

    @DeleteMapping(value = "/figures")
    public @ResponseBody void deleteAllFigures() {
        this.figureService.deleteAllFigures();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Figures deleted");
    }

    @PutMapping(value = "/figures/{id}")
    public @ResponseBody Figure updateFigure(@RequestBody Figure newFigure, @PathVariable Long id) {
        Optional<Figure> optFigure = this.figureService.findFigure(id);
        if (!optFigure.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
        }
        try {
            Figure figure = optFigure.get();
            figure.setName(newFigure.getName());
            return this.figureService.saveFigure(figure);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
