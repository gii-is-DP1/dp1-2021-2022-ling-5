package com.example.accessingdatamysql.achievement;

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
@RequestMapping(value = "/api")
public class AchievementController {
    @Autowired
    private AchievementService achievementService;

    @PostMapping(value = "/achievements") // Map ONLY POST Requests
    public @ResponseBody Achievement addNewAchievement(@RequestBody Achievement achievement) {
        return this.achievementService.saveAchievement(achievement);
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
    public @ResponseBody Achievement updateAchievement(@RequestBody Achievement newAchievement, @PathVariable Long id) {
        this.achievementService.findAchievement(id).map(achievement -> {
            achievement.setDescription(newAchievement.getDescription());
            return this.achievementService.saveAchievement(achievement);
        }).orElseGet(() -> {
            newAchievement.setId(id);
            return this.achievementService.saveAchievement(newAchievement);
        });
        return newAchievement;
    }
}
