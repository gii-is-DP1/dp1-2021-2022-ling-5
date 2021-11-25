package com.example.accessingdatamysql.achievement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<Achievement> findAllAchievements() {
        return StreamSupport.stream(achievementRepository.findAll().spliterator(), false).collect(Collectors.toList());

    }

    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    public void deleteAllAchievements() {
        achievementRepository.deleteAll();
    }
}
