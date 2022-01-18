package com.example.accessingdatamysql.friendship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "friendship", uniqueConstraints = @UniqueConstraint(columnNames = {"requested_id", "requester_id"}))
public class Friendship extends BaseEntity {

    @NotNull
    @Column(name = "friendshipState")
    private FriendshipState state;

    
    @ManyToOne
    private Player requester;
    
    @ManyToOne
    private Player requested;

    public Friendship() {
        this.state = FriendshipState.REQUESTED;
    }

    public Friendship(FriendshipState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", state='" + getState() + "'}";
    }
}
