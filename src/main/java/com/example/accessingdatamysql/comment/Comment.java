package com.example.accessingdatamysql.comment;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.forum.Forum;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Comment extends BaseEntity {

    public Comment(String text2, LocalDateTime now, Player player, Forum forum2) {
        this.text=text2;
        this.date=now;
        this.user=player;
        this.forum=forum2;
	}

    public Comment(){
        this.text="";
        this.date=LocalDateTime.now();
        this.user=null;
        this.forum=null;
    }

	@NotEmpty
    private String text;

    @NotNull
    private LocalDateTime date;

    @JsonIgnore
    @ManyToOne
    @NotNull
    private Forum forum;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "player_id")
    private Player user;
}
