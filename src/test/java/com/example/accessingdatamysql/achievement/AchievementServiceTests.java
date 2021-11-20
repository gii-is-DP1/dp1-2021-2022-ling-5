package com.example.accessingdatamysql.achievement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AchievementServiceTests {
    @Autowired
    protected AchievementService achievementService;

    @Test
    @Transactional
    public void shouldInsertAchievement() {
        List<Achievement> achievements = this.achievementService.findAllAchievements();
        int found = achievements.size();

        Achievement achievement = new Achievement("Streak10", "Streak 10 games");
        this.achievementService.saveAchievement(achievement);
        assertNotEquals(achievement.getId(), 0L);

        achievements = this.achievementService.findAllAchievements();
        assertEquals(achievements.size(), found + 1);
    }

    @Test
    public void shouldFindSingleAchievement() {
        Optional<Achievement> achievementOpt = this.achievementService.findAchievement(1L);
        if (achievementOpt.isPresent()) {
            Achievement achievement = achievementOpt.get();
            assertEquals(achievement.getName(), "Streak10");
            assertEquals(achievement.getDescription(), "Streak 10 games");
        }
    }

    @Test
    @Transactional
    void shouldUpdateAchievement() {
        Optional<Achievement> achievement = this.achievementService.findAchievement(1L);
        if (achievement.isPresent()) {
            String oldDescription = achievement.get().getDescription();
            String newDescription = oldDescription + "X";

            achievement.get().setDescription(newDescription);
            this.achievementService.saveAchievement(achievement.get());

            achievement = this.achievementService.findAchievement(1L);
            if (achievement.isPresent()) {
                assertEquals(achievement.get().getDescription(), newDescription);
            }
        }
    }

    @Test
    void shouldDeleteAchievement() {
        Achievement achievement = new Achievement("Streak30", "Streak 30 games");
        achievement = this.achievementService.saveAchievement(achievement);
        List<Achievement> achievements = this.achievementService.findAllAchievements();
        int found = achievements.size();

        this.achievementService.deleteAchievement(achievement.getId());
        achievements = this.achievementService.findAllAchievements();
        assertEquals(achievements.size(), found - 1);

    }

}
