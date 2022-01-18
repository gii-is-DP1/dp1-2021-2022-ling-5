package com.example.accessingdatamysql.achievement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.transaction.annotation.Transactional;

import com.example.accessingdatamysql.statistics.StatisticsService;
import com.example.accessingdatamysql.user.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

  private AchievementRepository achievementRepository;

  @Autowired
  private StatisticsService statisticsService;

  @Autowired
  private PlayerService playerService;

  @Autowired
  public AchievementService(AchievementRepository achievementRepository) {
    this.achievementRepository = achievementRepository;
  }

  @Transactional
  public Achievement saveAchievement(Achievement achievement)
      throws DataAccessException {
    achievementRepository.save(achievement);
    return achievement;
  }

  @Transactional(readOnly = true)
  public Optional<Achievement> findAchievement(Long id) {
    return achievementRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Achievement> findAllAchievements() {
    return StreamSupport
        .stream(achievementRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteAchievement(Long id) {
    achievementRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllAchievements() {
    achievementRepository.deleteAll();
  }

  @Transactional
  public List<Achievement> checkAchievements(Long playerId) {
    List<Achievement> achievements = new ArrayList<Achievement>();
    for (Achievement a : this.findAllAchievements()) {
      if (!a.getPlayers().contains(this.playerService.findPlayer(playerId).get())) {
        Achievement achievement = verifyAchievementPlayer(a, playerId);
        if (achievement != null) {
          achievements.add(achievement);
        }
      }
    }
    return achievements;
  }

  private Achievement verifyAchievementPlayer(Achievement achievement, Long playerId) {
    Integer points = 0;
    switch (achievement.getAchievementTypes()) {
      case POINTS:
        points = this.statisticsService.pointsByMinigames(playerId).stream()
            .collect(Collectors.summingInt(Integer::intValue));
        break;
      case POINTSFOSO:
        points = this.statisticsService.pointsByMinigames(playerId).get(0);
        break;
      case POINTSTORRE:
        points = this.statisticsService.pointsByMinigames(playerId).get(1);
        break;
      case POINTSREGALO:
        points = this.statisticsService.pointsByMinigames(playerId).get(2);
        break;
      default:
        break;
    }
    if (points >= achievement.getRequirement()) {
      return achievement;
    } else {
      return null;
    }
  }
}
