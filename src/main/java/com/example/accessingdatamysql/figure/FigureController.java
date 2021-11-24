package com.example.accessingdatamysql.figure;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.user.Admin;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.card.Card;

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
public class FigureController {
    @Autowired
    private FigureService figureService;

    @PostMapping(value = "/figures") // Map ONLY POST Requests
    public @ResponseBody Figure addNewFigure(@RequestBody Figure figure) {
        figure.setAdmins(new ArrayList<Admin>());
        figure.setPlayers(new ArrayList<Player>());
        figure.setCards(new ArrayList<Card>());

        return this.figureService.saveFigure(figure);
    }

    @GetMapping(value = "/figures")
    public @ResponseBody Iterable<Figure> getAllFigures() {
        return this.figureService.findAllFigures();
    }

    @GetMapping(value = "/figures/{id}")
    public @ResponseBody Optional<Figure> getFigureById(@PathVariable Long id) {
        return this.figureService.findFigure(id);
    }

    @DeleteMapping(value = "/figures/{id}")
    public @ResponseBody String deleteFigure(@PathVariable Long id) {
        this.figureService.deleteFigure(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/figures")
    public @ResponseBody String deleteAllFigures() {
        this.figureService.deleteAllFigures();
        return "Deleted all";
    }

    @PutMapping(value = "/figures/{id}")
    public @ResponseBody Figure updateFigure(@RequestBody Figure newFigure, @PathVariable Long id) {
        this.figureService.findFigure(id).map(figure -> {
            figure.setName(newFigure.getName());
            return this.figureService.saveFigure(figure);
        }).orElse(null);
        return null;
    }
}
