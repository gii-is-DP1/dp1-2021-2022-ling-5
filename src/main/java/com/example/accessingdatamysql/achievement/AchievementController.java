package com.example.accessingdatamysql.achievement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerController;
import com.example.accessingdatamysql.user.PlayerService;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Controller
@RequestMapping(value = "/api")
public class AchievementController {
    @Autowired
    private AchievementService achievementService;

    @Autowired
    private FigureService figureService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerController playerController;

    @PostMapping(value = "/achievements/figures/{figureId}")
    public @ResponseBody Achievement addAchievementToFigure(@RequestBody Achievement achievement,
            @PathVariable Long figureId) {
        Optional<Figure> figure = this.figureService.findFigure(figureId);
        if (figure.isPresent()) {
            achievement.setPlayers(new ArrayList<Player>());

            figure.get().setAchievement(achievement);
            achievement.setFigure(figure.get());
            return this.achievementService.saveAchievement(achievement);
        }
        return null;
    }

    @GetMapping(value = "/achievements")
    public @ResponseBody Iterable<Achievement> getAllAchievements() {
        return this.achievementService.findAllAchievements();
    }

    @GetMapping(value = "/achievements/{id}")
    public @ResponseBody Optional<Achievement> getAchievementById(@PathVariable Long id) {
        return this.achievementService.findAchievement(id);
    }

    @DeleteMapping(value = "/achievements/{id}")
    public @ResponseBody String deleteAchievement(@PathVariable Long id) {
        this.achievementService.deleteAchievement(id);
        return "Deleted";
    }

    @DeleteMapping(value = "/achievements")
    public @ResponseBody String deleteAllAchievements() {
        this.achievementService.deleteAllAchievements();
        return "Deleted all";
    }

    @PutMapping(value = "/achievements/{id}")
    public @ResponseBody Achievement updateAchievement(@RequestBody Achievement newAchievement, 
        @PathVariable Long id) throws NotFound{
        try{
            Achievement oldAchievement = achievementService.findAchievement(id).get();
            oldAchievement.setName(newAchievement.getName());
            oldAchievement.setDescription(newAchievement.getDescription());
            oldAchievement.setAchievementTypes(newAchievement.getAchievementTypes());
            oldAchievement.setRequirement(newAchievement.getRequirement());
            return this.achievementService.saveAchievement(oldAchievement);
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No achivement found with that id");
        }
    }

    @PostMapping(value = "/achievements")
    public @ResponseBody Achievement createAchievement(@RequestBody Achievement achievement){
        return this.achievementService.saveAchievement(achievement);
    }

    @GetMapping(value = "/achievements/players/{playerId}")
    public @ResponseBody List<Achievement> getAchievementByPlayer(@PathVariable Long playerId){
        return this.achievementService.findAllAchievements().stream()
            .filter(a->a.getPlayers().contains(this.playerService.findPlayer(playerId).get()))
            .collect(Collectors.toList());
    }

    @PutMapping(value = "/achievements/players/{playerId}")
    public @ResponseBody void checkAchievements(@PathVariable Long playerId){
        List<Achievement> achievements = this.achievementService.checkAchievements(playerId);
        for(Achievement a: achievements){
            this.playerController.addNewAchievementToUser(playerId, a.getId());
        }
    }
}
