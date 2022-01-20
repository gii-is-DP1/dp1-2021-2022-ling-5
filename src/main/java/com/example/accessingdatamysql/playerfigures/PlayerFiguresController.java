package com.example.accessingdatamysql.playerfigures;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;

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
public class PlayerFiguresController {
    @Autowired
    private PlayerFiguresService playerFiguresService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private FigureService figureService;

    @PostMapping(value = "/players/{playerId}/addfigures") 
    public @ResponseBody void addNewPlayerFigures(@PathVariable Long playerId) {
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (player.isPresent()) {
            playerFiguresService.createAll(player.get());
        }
    }

    @PostMapping(value = "/figure/{figureId}/players/{playerId}/playerfigures") 
    public @ResponseBody PlayerFigures addNewResult(@RequestBody PlayerFigures result, @PathVariable Long figureId,
            @PathVariable Long playerId) {
        Optional<Figure> figure = this.figureService.findFigure(figureId);
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (figure.isPresent() && player.isPresent()) {
            result.setFigure(figure.get());
            result.setPlayer(player.get());
            return this.playerFiguresService.savePlayerFigures(result);
        }
        return null;
    }

    @PutMapping(value = "/player/{playerId}/figure/{figureId}/add")
    public @ResponseBody PlayerFigures addOne(@PathVariable Long figureId,
            @PathVariable Long playerId) {
        PlayerFigures pf=this.playerFiguresService.getByData(figureId,playerId);
        pf.setSuccesful((pf.getSuccesful()+1));
        return this.playerFiguresService.savePlayerFigures(pf);
    }

    @GetMapping(value = "/playerfigures")
    public @ResponseBody Iterable<PlayerFigures> getAllPlayerFigures() {
        return this.playerFiguresService.findAllPlayerFigures();
    }

    @GetMapping(value = "/playerfigures/{id}")
    public @ResponseBody Optional<PlayerFigures> getPlayerFiguresById(@PathVariable Long id) {
        return this.playerFiguresService.findPlayerFigures(id);
    }

    @GetMapping(value = "/figures/{figureId}/playerfigures")
    public @ResponseBody List<PlayerFigures> getAllPlayerFiguresByFigure(@PathVariable Long figureId) {
        Optional<Figure> game = this.figureService.findFigure(figureId);
        if (game.isPresent()) {
            return this.playerFiguresService.findAllPlayerFiguresByFigure(figureId);
        } else {
            return new ArrayList<PlayerFigures>();
        }
    }

    @GetMapping(value = "/players/{playerId}/playerfigures")
    public @ResponseBody List<PlayerFigures> getAllPlayerFiguresByPlayer(@PathVariable Long playerId) {
        Optional<Player> player = this.playerService.findPlayer(playerId);
        if (player.isPresent()) {
            return this.playerFiguresService.findAllPlayerFiguresByPlayer(playerId);
        } else {
            return new ArrayList<PlayerFigures>();
        }
    }

    @DeleteMapping(value = "/playerfigures/{id}")
    public @ResponseBody String deletePlayerFigure(@PathVariable Long id) {
        this.playerFiguresService.deletePlayerFigures(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/PlayerFigures")
    public @ResponseBody String deleteAllPlayerFigures() {
        this.playerFiguresService.deleteAllPlayerFigures();
        return "Deleted all";
    }

    @DeleteMapping(value = "/figure/{figureId}/playerfigures")
    public @ResponseBody String deleteAllPlayerFiguresByFigure(@PathVariable Long figureId) {
        this.playerFiguresService.deleteAllPlayerFiguresByFigure(figureId);
        return "Deleted all";
    }

    @DeleteMapping(value = "/players/{playerId}/playerfigures")
    public @ResponseBody String deleteAllPlayerFiguresByPlayer(@PathVariable Long playerId) {
        this.playerFiguresService.deleteAllPlayerFiguresByPlayer(playerId);
        return "Deleted all";
    }
}
