package com.example.accessingdatamysql.change;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "change")
public class Change extends BaseEntity {

    @ManyToOne
    private Account account;

    @Column(name = "date")
    private Date date;

    @Column(name = "what")
    private String what;

    @Column(name = "before")
    private String before;

    @Column(name = "after")
    private String after;

}
