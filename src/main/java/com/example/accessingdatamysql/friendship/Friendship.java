package com.example.accessingdatamysql.friendship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "friendship")
public class Friendship extends BaseEntity {

    @NotNull
    @Column(name = "friendshipState")
    private FriendshipState state;

    @JsonIgnore
    @ManyToOne
    private Player requester;

    @JsonIgnore
    @ManyToOne
    private Player requested;
}
