package com.example.accessingdatamysql.avatar;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.accessingdatamysql.achievement.Achievement;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "avatar")
public class Avatar extends BaseEntity {

    @OneToMany(mappedBy = "avatar")
    private Collection<Account> accounts;

    @OneToOne(mappedBy = "avatar")
    private Achievement achievement;

    @Column(name = "path")
    private String path;

}
