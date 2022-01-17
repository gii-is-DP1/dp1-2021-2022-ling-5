package com.example.accessingdatamysql.user;

import java.util.ArrayList;
import java.util.Optional;

import com.example.accessingdatamysql.achievement.Achievement;
import com.example.accessingdatamysql.achievement.AchievementService;
import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.role.Role;
import com.example.accessingdatamysql.role.RoleService;

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
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/api")
public class PlayerController {
  @Autowired
  private PlayerService playerService;

  @Autowired
  private FigureService figureService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private AchievementService achievementService;

  @PostMapping(value = "/players") // Map ONLY POST Requests
  public @ResponseBody Player addNewPlayer(@RequestBody Player player) {

    Optional<Role> role = this.roleService.findRole(1L);
    Optional<Figure> figure = this.figureService.findFigure(1L);
    if (!figure.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
    if (!role.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
    try {
      player.setFigure(figure.get());
      player.setRole(role.get());
      return this.playerService.savePlayer(player);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PostMapping(value = "/players/{playerId}/achievements/{achievementId}")
  public @ResponseBody Player addNewAchievementToUser(@PathVariable Long playerId, @PathVariable Long achievementId) {
    Optional<Player> player = this.playerService.findPlayer(playerId);
    Optional<Achievement> achievement = this.achievementService.findAchievement(achievementId);
    if (!player.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
    if (!achievement.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found");
    try {
      if (player.get().getAchievements() == null)
        player.get().setAchievements(new ArrayList<Achievement>());
      if (achievement.get().getPlayers() == null)
        achievement.get().setPlayers(new ArrayList<Player>());

      if (!player.get().getAchievements().contains(achievement.get()))
        player.get().getAchievements().add(achievement.get());
      if (!achievement.get().getPlayers().contains(player.get()))
        achievement.get().getPlayers().add(player.get());
      this.playerService.savePlayer(player.get());

      return player.get();

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

  }

  @GetMapping(value = "/players")
  public @ResponseBody Iterable<Player> getAllPlayers() {
    return this.playerService.findAllPlayers();
  }

  @GetMapping(value = "/players/{id}")
  public @ResponseBody Player getPlayerById(@PathVariable Long id) {
    Optional<Player> pOptional = this.playerService.findPlayer(id);
    if (pOptional.isPresent())
      return pOptional.get();
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
  }

  @GetMapping(value = "/players/names/{nickname}")
  public @ResponseBody Player getPlayerByNickname(@PathVariable String nickname) {
    Optional<Player> pOptional = this.playerService.findPlayerByNickname(nickname);
    if (pOptional.isPresent())
      return pOptional.get();
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
  }

  @DeleteMapping(value = "/players/{id}")
  public @ResponseBody void deletePlayer(@PathVariable Long id) {
    Optional<Player> pOptional = this.playerService.findPlayer(id);
    if (pOptional.isPresent()) {
      this.playerService.deletePlayer(id);
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Player deleted");
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
  }

  @DeleteMapping(value = "/players")
  public @ResponseBody void deleteAllPlayers() {
    this.playerService.deleteAllPlayers();
    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Players deleted");
  }

  @DeleteMapping(value = "/players/{playerId}/achievements/{achievementId}")
  public @ResponseBody void deleteAchievementFromPlayer(@PathVariable Long playerId,
      @PathVariable Long achievementId) {
    Optional<Player> player = this.playerService.findPlayer(playerId);
    Optional<Achievement> achievement = this.achievementService.findAchievement(achievementId);
    if (!player.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
    if (!achievement.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found");
    try {
      if (player.get().getAchievements() == null) {
        player.get().setAchievements(new ArrayList<Achievement>());
        if (achievement.get().getPlayers() == null)
          achievement.get().setPlayers(new ArrayList<Player>());
      }

      player.get().getAchievements().remove(achievement.get());
      achievement.get().getPlayers().remove(player.get());
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Deleted achievement from player");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping(value = "/players/{id}")
  public @ResponseBody Player updatePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
    Optional<Player> pOptional = this.playerService.findPlayer(id);
    if (!pOptional.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player is not found");
    try {
      Player player = pOptional.get();
      player.setName(newPlayer.getName());
      player.setSurname(newPlayer.getSurname());
      player.setPlayerState(newPlayer.getPlayerState());
      return this.playerService.savePlayer(player);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping(value = "/figures/{figureId}/players/{playerId}")
  public @ResponseBody Player updateFigurePlayer(@PathVariable Long playerId, @PathVariable Long figureId) {
    Optional<Figure> fOptional = this.figureService.findFigure(figureId);
    if (!fOptional.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Figure not found");
    Optional<Player> optionalPlayer = this.playerService.findPlayer(playerId);
    if (!optionalPlayer.isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
    try {
      Player player = optionalPlayer.get();
      player.setFigure(fOptional.get());
      return this.playerService.savePlayer(player);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
