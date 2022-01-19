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
import org.springframework.web.server.ResponseStatusException;

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
            try {
                return this.achievementService.saveAchievement(achievement);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Figure not found, it's not possible to create the achievement");
    }

    @GetMapping(value = "/achievements")
    public @ResponseBody Iterable<Achievement> getAllAchievements() {
        return this.achievementService.findAllAchievements();
    }

    @GetMapping(value = "/achievements/{id}")
    public @ResponseBody Achievement getAchievementById(@PathVariable Long id) {
        Optional<Achievement> achievement = this.achievementService.findAchievement(id);
        if (achievement.isPresent())
            return achievement.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found");
    }

    @DeleteMapping(value = "/achievements/{id}")
    public @ResponseBody void deleteAchievement(@PathVariable Long id) {
        Optional<Achievement> achievement = this.achievementService.findAchievement(id);
        if (achievement.isPresent()) {
            this.achievementService.deleteAchievement(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Achievement deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found");

    }

    @DeleteMapping(value = "/achievements")
    public @ResponseBody void deleteAllAchievements() {
        this.achievementService.deleteAllAchievements();
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Achievements deleted");
    }

    @PutMapping(value = "/achievements/{id}")
    public @ResponseBody Achievement updateAchievement(@RequestBody Achievement newAchievement,
            @PathVariable Long id) throws NotFound {
        try {
            Achievement oldAchievement = achievementService.findAchievement(id).get();
            oldAchievement.setName(newAchievement.getName());
            oldAchievement.setDescription(newAchievement.getDescription());
            oldAchievement.setAchievementTypes(newAchievement.getAchievementTypes());
            oldAchievement.setRequirement(newAchievement.getRequirement());
            return this.achievementService.saveAchievement(oldAchievement);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No achivement found with that id");
        }
    }

    @PostMapping(value = "/achievements")
    public @ResponseBody Achievement createAchievement(@RequestBody Achievement achievement) {
        try {
            return this.achievementService.saveAchievement(achievement);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/achievements/players/{playerId}")
    public @ResponseBody List<Achievement> getAchievementByPlayer(@PathVariable Long playerId) {
        try {
            return this.achievementService.findAllAchievements().stream()
                    .filter(a -> a.getPlayers().contains(this.playerService.findPlayer(playerId).get()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }

    @PutMapping(value = "/achievements/players/{playerId}")
    public @ResponseBody void checkAchievements(@PathVariable Long playerId) {
        List<Achievement> achievements = this.achievementService.checkAchievements(playerId);
        for (Achievement a : achievements) {
            this.playerController.addNewAchievementToUser(playerId, a.getId());
        }
    }

    @GetMapping(value = "/achievementTypes")
    public @ResponseBody List<AchievementTypes> getAllAchievementTypes() {
        return AchievementTypes.getTypes().entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
    }
}
