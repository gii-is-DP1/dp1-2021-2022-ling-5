package com.example.accessingdatamysql.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.accessingdatamysql.achievement.Achievement;
import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.friendship.Friendship;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.modification.Modification;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.role.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Player") // This tells Hibernate to make a table out of this class
public class Player extends Account {

  @Column(name = "playerState")
  private PlayerState playerState;

  @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
  private List<Result> results;

  @ManyToMany
  private List<Game> gamesPlayed;

  @ManyToMany
  private List<Achievement> achievements;

  @OneToMany(mappedBy = "requester", cascade = CascadeType.REMOVE)
  private List<Friendship> startToFollow;

  @OneToMany(mappedBy = "requested", cascade = CascadeType.REMOVE)
  private List<Friendship> requestedToFollow;

  @ManyToOne
  private Role role;

  @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
  private List<Modification> modifications;

  @ManyToOne
  private Figure figure;

  public Player() {
    this.name = "";
    this.surname = "";
    this.password = "";
    this.email = "";
    this.nickname = "";
    this.playerState = PlayerState.NO_PLAY;
  }

  public Player(String name, String surname, String password, String email, String nickname, PlayerState playerState) {
    this.name = name;
    this.surname = surname;
    this.password = password;
    this.email = email;
    this.nickname = nickname;
    this.playerState = playerState;
  }
  public Player(String name, String surname, String password, String email, String nickname){
    this.name = name;
    this.surname = surname;
    this.password = password;
    this.email = email;
    this.nickname = nickname;
    this.playerState = PlayerState.NO_PLAY;
  }

  @Override
  public String toString() {
    return "{" + super.toString() + ", playerState='" + getPlayerState() + "'" + ", results='" + getResults() + "'"
        + ", gamesPlayed='" + getGamesPlayed() + "'" + ", achievements='" + getAchievements() + "'"
        + ", startToFollow='" + getStartToFollow() + "'" + ", requestedToFollow='" + getRequestedToFollow() + "'"
        + ", role='" + getRole() + "'" + ", modifications='" + getModifications() + "'" + ", figure='" + getFigure()
        + "'" + "}";
  }

}