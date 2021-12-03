package com.example.accessingdatamysql.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.achievement.Achievement;
import com.example.accessingdatamysql.achievement.AchievementService;
import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.modification.Modification;
import com.example.accessingdatamysql.role.Role;
import com.example.accessingdatamysql.role.RoleService;

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
public class PlayerController {
  @Autowired
  private PlayerService playerService;

  @Autowired
  private FigureService figureService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private AchievementService achievementService;

  @PostMapping(value = "/roles/{roleId}/figures/{figureId}/players") // Map ONLY POST Requests
  public @ResponseBody Player addNewPlayer(@RequestBody Player player, @PathVariable Long roleId,
      @PathVariable Long figureId) {
    player.setModifications(new ArrayList<Modification>());

    Optional<Role> role = this.roleService.findRole(roleId);
    Optional<Figure> figure = this.figureService.findFigure(figureId);
    if (figure.isPresent() && role.isPresent()) {
      player.setFigure(figure.get());
      player.setRole(role.get());
      return this.playerService.savePlayer(player);
    }
    return null;
  }

  @PostMapping(value = "/players/{playerId}/achievements/{achievementId}")
  public @ResponseBody Player addNewAchievementToUser(@PathVariable Long playerId, @PathVariable Long achievementId) {
    Optional<Player> player = this.playerService.findPlayer(playerId);
    Optional<Achievement> achievement = this.achievementService.findAchievement(achievementId);
    if (!player.isPresent())
      return null;
    if (achievement.isPresent()) {

      if (player.get().getAchievements() == null)
        player.get().setAchievements(new ArrayList<Achievement>());
      if (achievement.get().getPlayers() == null)
        achievement.get().setPlayers(new ArrayList<Player>());

      player.get().getAchievements().add(achievement.get());
      achievement.get().getPlayers().add(player.get());
      this.playerService.savePlayer(player.get());
    }
    return player.get();
  }

  @GetMapping(value = "/players")
  public @ResponseBody Iterable<Player> getAllPlayers() {
    return this.playerService.findAllPlayers();
  }

  @GetMapping(value = "/roles/{roleId}/players")
  public @ResponseBody List<Player> getAllPlayersByRole(@PathVariable Long roleId) {
    return this.playerService.findAllPlayersByRole(roleId);
  }

  @GetMapping(value = "/players/{id}")
  public @ResponseBody Optional<Player> getPlayerById(@PathVariable Long id) {
    return this.playerService.findPlayer(id);
  }

  // @GetMapping(value = "/players/games/{gameId}")
  // public @ResponseBody List<Player> getAllPlayersByGame(@PathVariable Long
  // gameId) {
  // List<Player> players = this.playerService.findAllPlayers();
  // return players.stream().filter(player ->
  // player.getGamesPlayed().contains(this.gameService.findGame(gameId).get()))
  // .collect(Collectors.toList());
  // }

  @DeleteMapping(value = "/players/{id}")
  public @ResponseBody String deletePlayer(@PathVariable Long id) {
    this.playerService.deletePlayer(id);
    return "Deleted";
  }

  @DeleteMapping(value = "/players")
  public @ResponseBody String deleteAllPlayers() {
    this.playerService.deleteAllPlayers();
    return "Deleted all";
  }

  @DeleteMapping(value = "/roles/{roleId}/players")
  public @ResponseBody String deleteAllPlayersByRole(@PathVariable Long roleId) {
    this.playerService.deleteAllPlayersByRole(roleId);
    return "Deleted all";
  }

  @DeleteMapping(value = "/players/{playerId}/achievements/{achievementId}")
  public @ResponseBody String deleteAchievementFromPlayer(@PathVariable Long playerId,
      @PathVariable Long achievementId) {
    Optional<Player> player = this.playerService.findPlayer(playerId);
    Optional<Achievement> achievement = this.achievementService.findAchievement(achievementId);
    if (!player.isPresent())
      return "User not found";
    if (!achievement.isPresent())
      return "Achievement not found";

    if (player.get().getAchievements() == null) {
      player.get().setAchievements(new ArrayList<Achievement>());
      if (achievement.get().getPlayers() == null)
        achievement.get().setPlayers(new ArrayList<Player>());
    }

    player.get().getAchievements().remove(achievement.get());
    achievement.get().getPlayers().remove(player.get());
    return "Achievement deleted from player";

  }

  @PutMapping(value = "/players/{id}")
  public @ResponseBody Player updatePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
    this.playerService.findPlayer(id).map(player -> {
      player.setName(newPlayer.getName());
      player.setSurname(newPlayer.getSurname());
      player.setPlayerState(newPlayer.getPlayerState());
      return this.playerService.savePlayer(player);
    }).orElse(null);
    return null;
  }

  @PutMapping(value = "/figures/{figureId}/players/{playerId}")
  public @ResponseBody String updateFigurePlayer(@PathVariable Long playerId, @PathVariable Long figureId) {
    return this.figureService.findFigure(figureId).map(figure -> {
      Optional<Player> optionalPlayer = this.playerService.findPlayer(playerId);
      if (optionalPlayer.isPresent()) {
        Player player = optionalPlayer.get();
        player.setFigure(figure);
        this.playerService.savePlayer(player);
        return "Saved";
      } else {
        return "Player not found";
      }
    }).orElse("Figure not found");
  }

  @PutMapping(value = "/roles/{roleId}/players/{playerId}")
  public @ResponseBody String updateRolePlayer(@PathVariable Long playerId, @PathVariable Long roleId) {
    return this.roleService.findRole(roleId).map(role -> {
      Optional<Player> optionalPlayer = this.playerService.findPlayer(playerId);
      if (optionalPlayer.isPresent()) {
        Player player = optionalPlayer.get();
        player.setRole(role);
        this.playerService.savePlayer(player);
        return "Saved";
      } else {
        return "Player not found";
      }
    }).orElse("Role not found");
  }

}
