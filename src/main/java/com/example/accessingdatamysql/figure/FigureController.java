package com.example.accessingdatamysql.figure;

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
public class FigureController {
    @Autowired
    private FigureService figureService;

    @PostMapping(path = "/figures") // Map ONLY POST Requests
    public @ResponseBody Figure addNewFigure(@RequestBody Figure figure) {
        return this.figureService.saveFigure(figure);
    }

    @GetMapping(path = "/figures")
    public @ResponseBody Iterable<Figure> getAllFigures() {
        return this.figureService.findAllFigures();
    }

    @GetMapping(path = "/figures/{id}")
    public @ResponseBody Optional<Figure> getFigureById(@PathVariable Long id) {
        return this.figureService.findFigure(id);
    }

    @DeleteMapping(path = "/figures/{id}")
    public @ResponseBody String deleteFigure(@PathVariable Long id) {
        this.figureService.deleteFigure(id);
        return "Deleted";
    }

    @DeleteMapping(path = "/figures")
    public @ResponseBody String deleteAllFigures() {
        this.figureService.deleteAllFigures();
        return "Deleted all";
    }

    @PutMapping("/figures/{id}")
    public @ResponseBody Figure updateFigure(@RequestBody Figure newFigure, @PathVariable Long id) {
        this.figureService.findFigure(id).map(figure -> {
            figure.setPath(newFigure.getPath());
            return this.figureService.saveFigure(figure);
        }).orElseGet(() -> {
            newFigure.setId(id);
            return this.figureService.saveFigure(newFigure);
        });
        return newFigure;
    }
}
