package com.example.accessingdatamysql.user;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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

  @Column(name = "gamesWon")
  private int gamesWon;

  @Column(name = "playerState")
  private PlayerState playerState;

  @OneToMany(mappedBy = "player")
  private Collection<Result> results;

  @ManyToMany
  private Collection<Game> gamesPlayed;

  @ManyToMany
  private Collection<Achievement> achievements;

  @OneToMany(mappedBy = "requester")
  private Collection<Friendship> startToFollow;

  @OneToMany(mappedBy = "requested")
  private Collection<Friendship> requestedToFollow;

}