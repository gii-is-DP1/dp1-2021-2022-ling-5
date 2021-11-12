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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class AchievementController {
    @Autowired
    private AchievementService adminService;

    @RequestMapping(value = "/admins", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody Achievement addNewAchievement(@RequestBody Achievement admin) {
        return this.adminService.saveAchievement(admin);
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public @ResponseBody Iterable<Achievement> getAllAchievements() {
        return this.adminService.findAllAchievements();
    }

    @RequestMapping(value = "/admins/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Achievement> getAchievementById(@PathVariable Long id) {
        return this.adminService.findAchievement(id);
    }

    @RequestMapping(value = "/admins/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAchievement(@PathVariable Long id) {
        this.adminService.deleteAchievement(id);
        return "Deleted";
    }

    @RequestMapping(value = "/admins", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllAchievements() {
        this.adminService.deleteAllAchievements();
        return "Deleted all";
    }

    @RequestMapping(value = "/admins/{id}", method = RequestMethod.PUT)
    public @ResponseBody Achievement updateAchievement(@RequestBody Achievement newAchievement, @PathVariable Long id) {
        this.adminService.findAchievement(id).map(admin -> {
            admin.setDescription(newAchievement.getDescription());
            return this.adminService.saveAchievement(admin);
        }).orElseGet(() -> {
            newAchievement.setId(id);
            return this.adminService.saveAchievement(newAchievement);
        });
        return newAchievement;
    }
}
