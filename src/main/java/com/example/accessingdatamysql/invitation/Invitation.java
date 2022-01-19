package com.example.accessingdatamysql.invitation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    @ManyToOne
    private Game game;

    @NotNull
    @ManyToOne
    private Player requester;

    @NotNull
    @ManyToOne
    private Player requested;

    @Override
    public String toString() {
        return "Invitation [creationDate=" + creationDate + ", game=" + game + ", requested=" + requested
                + ", requester=" + requester + "]";
    }

    public Invitation() {
        ;
        this.creationDate = LocalDateTime.now();
    }

}