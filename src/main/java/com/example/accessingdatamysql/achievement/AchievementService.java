package com.example.accessingdatamysql.achievement;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {
    private AchievementRepository achievementRepository;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Transactional
    public Achievement saveAchievement(Achievement achievement) throws DataAccessException {
        achievementRepository.save(achievement);
        return achievement;
    }

    public Optional<Achievement> findAchievement(Long id) {
        return achievementRepository.findById(id);
    }

    public Iterable<Achievement> findAllAchievements() {
        return achievementRepository.findAll();
    }

    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    public void deleteAllAchievements() {
        achievementRepository.deleteAll();
    }
}
