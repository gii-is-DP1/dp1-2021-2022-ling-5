package com.example.accessingdatamysql.user;

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
public class PlayerController {
  @Autowired
  private PlayerService playerService;

  @RequestMapping(value = "/players", method = RequestMethod.POST) // Map ONLY POST Requests
  public @ResponseBody Player addNewPlayer(@RequestBody Player player) {
    return this.playerService.savePlayer(player);
  }

  @RequestMapping(value = "/players", method = RequestMethod.GET)
  public @ResponseBody Iterable<Player> getAllPlayers() {
    return this.playerService.findAllPlayers();
  }

  @RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
  public @ResponseBody Optional<Player> getPlayerById(@PathVariable Long id) {
    return this.playerService.findPlayer(id);
  }

  @RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
  public @ResponseBody String deletePlayer(@PathVariable Long id) {
    this.playerService.deletePlayer(id);
    return "Deleted";
  }

  @RequestMapping(value = "/players", method = RequestMethod.DELETE)
  public @ResponseBody String deleteAllPlayers() {
    this.playerService.deleteAllPlayers();
    return "Deleted all";
  }

  @RequestMapping(value = "/players/{id}", method = RequestMethod.PUT)
  public @ResponseBody Player updatePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
    this.playerService.findPlayer(id).map(player -> {
      player.setName(newPlayer.getName());
      player.setSurname(newPlayer.getSurname());
      player.setPassword(newPlayer.getPassword());
      player.setEmail(newPlayer.getEmail());
      player.setNickname(newPlayer.getNickname());
      player.setGamesWon(newPlayer.getGamesWon());
      player.setPlayerState(newPlayer.getPlayerState());
      return this.playerService.savePlayer(player);
    }).orElseGet(() -> {
      newPlayer.setId(id);
      return this.playerService.savePlayer(newPlayer);
    });
    return newPlayer;
  }
}