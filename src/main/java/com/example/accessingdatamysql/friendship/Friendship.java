package com.example.accessingdatamysql.friendship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "friendship")
public class Friendship extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "friendshipState")
    private FriendshipState state;

    @ManyToOne
    private Player requester;

    @ManyToOne
    private Player requested;
}
