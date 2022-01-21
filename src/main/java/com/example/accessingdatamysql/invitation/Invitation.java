package com.example.accessingdatamysql.invitation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invitation")
public class Invitation extends BaseEntity {

    private LocalDateTime creationDate;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Player requester;

    @ManyToOne
    private Player requested;

    public Invitation() {
        this.creationDate = LocalDateTime.now();
    }

    public Invitation(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Invitation(LocalDateTime creationDate, Player requester, Player requested, Game game) {
        this.creationDate = creationDate;
        this.requester = requester;
        this.requested = requested;
        this.game = game;
    }

    @Override
    public String toString() {
        return "Invitation [creationDate=" + creationDate + ", game=" + game + ", requested=" + requested
                + ", requester=" + requester + "]";
    }

}