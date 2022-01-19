package com.example.accessingdatamysql.game;

import com.example.accessingdatamysql.minigame.Minigame;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game extends BaseEntity {

  public static final int MIN_SIZE = 3;
  public static final int MAX_SIZE = 50;

  @Size(min = MIN_SIZE, max = MAX_SIZE)
  @NotEmpty
  @NotNull
  @Column(unique = true)
  private String name;

  private State state;

  private Date startTime;

  private Date endTime;

  @NotNull
  private Long creator;

  private Long winner;

  @ManyToMany(mappedBy = "games")
  @Size(max = 3)
  private List<Minigame> minigames;

  @JsonIgnore
  @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
  private List<Result> results;

  @JsonIgnore
  @ManyToMany(mappedBy = "gamesPlayed")
  @Size(max = 8)
  private List<Player> players;

  public Game() {
    this.name = "";
    this.state = State.UNSTARTED;
    this.startTime = new Date(System.currentTimeMillis());
    this.endTime = new Date(System.currentTimeMillis());
    this.creator = 0L;
    this.winner = 0L;
  }

  public Game(String name, State state, Date startTime, Date endTime, Long creator, Long winner) {
    this.name = name;
    this.state = state;
    this.startTime = startTime;
    this.endTime = endTime;
    this.creator = creator;
    this.winner = winner;
  }

  @Override
  public String toString() {
    return ("{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", state='" + getState() + "'"
        + ", startTime='" + getStartTime() + "'" + ", endTime='" + getEndTime() + "'" + ", creator='" + getCreator()
        + "'" + ", winner='" + getWinner() + "'" + ", minigames='" + getMinigames() + "'" + "}");
  }
}
