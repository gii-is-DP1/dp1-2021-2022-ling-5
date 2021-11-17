package com.example.accessingdatamysql.user;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.achievement.Achievement;
import com.example.accessingdatamysql.friendship.Friendship;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.result.Result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Player") // This tells Hibernate to make a table out of this class
public class Player extends Account {

  // @NotNull
  // @NotEmpty
  // @Column(name = "gamesWon")
  // private int gamesWon;

  @NotEmpty
  @NotNull
  @Column(name = "playerState")
  private PlayerState playerState;

  @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
  private Collection<Result> results;

  @ManyToMany
  private Collection<Game> gamesPlayed;

  @ManyToMany
  private Collection<Achievement> achievements;

  @OneToMany(mappedBy = "requester", cascade = CascadeType.REMOVE)
  private Collection<Friendship> startToFollow;

  @OneToMany(mappedBy = "requested", cascade = CascadeType.REMOVE)
  private Collection<Friendship> requestedToFollow;

}