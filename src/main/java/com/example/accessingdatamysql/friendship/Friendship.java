package com.example.accessingdatamysql.friendship;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "friendship")
public class Friendship extends BaseEntity {

    @Column(name = "friendshipState")
    private FriendshipState state;

    @ManyToOne
    private Player requester;

    @ManyToOne
    private Player requested;
}
