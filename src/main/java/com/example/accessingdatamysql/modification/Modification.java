package com.example.accessingdatamysql.modification;

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
@Table(name = "modification")
public class Modification extends BaseEntity {

    @ManyToOne
    private Account account;

    @Column(name = "moment")
    private Date moment;

    @Column(name = "what")
    private String what;

    @Column(name = "beforeModification")
    private String beforeModification;

    @Column(name = "afterModification")
    private String afterModification;

}
