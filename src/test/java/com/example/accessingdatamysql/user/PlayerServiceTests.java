package com.example.accessingdatamysql.user;

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
public class PlayerServiceTests {

  @Autowired
  protected PlayerService playerService;

  @Test
  @Transactional
  public void shouldInsertPlayer() {
    List<Player> players = this.playerService.findAllPlayers();
    int found = players.size();

    Player player = new Player(
      "Uno",
      "uno",
      "uno",
      "uno2@gmail.com",
      "unito2",
      PlayerState.NO_PLAY
    );
    this.playerService.savePlayer(player);
    assertNotEquals(player.getId(), 0L);

    players = this.playerService.findAllPlayers();
    assertEquals(players.size(), found + 1);
  }

  @Test
  public void shouldFindSinglePlayer() {
    Optional<Player> playerOpt = this.playerService.findPlayer(1L);
    if (playerOpt.isPresent()) {
      Player player = playerOpt.get();
      assertEquals(player.getName(), "Uno");
    }
  }

  @Test
  @Transactional
  void shouldUpdatePlayer() {
    Optional<Player> player = this.playerService.findPlayer(1L);
    if (player.isPresent()) {
      String oldName = player.get().getName();
      String newName = oldName + "X";

      player.get().setName(newName);
      this.playerService.savePlayer(player.get());

      player = this.playerService.findPlayer(1L);
      if (player.isPresent()) {
        assertEquals(player.get().getName(), newName);
      }
    }
  }

  @Test
  void shouldDeletePlayer() {
    Player player = new Player(
      "Dos",
      "dos",
      "dos",
      "dos2@gmail.com",
      "dosito2",
      PlayerState.NO_PLAY
    );
    player = this.playerService.savePlayer(player);
    List<Player> players = this.playerService.findAllPlayers();
    int found = players.size();

    this.playerService.deletePlayer(player.getId());
    players = this.playerService.findAllPlayers();
    assertEquals(players.size(), found - 1);
  }
}
