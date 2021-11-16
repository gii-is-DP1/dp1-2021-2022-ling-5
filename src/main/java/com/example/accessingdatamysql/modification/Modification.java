package com.example.accessingdatamysql.modification;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "modification")
public class Modification extends BaseEntity {

    @NotNull
    @Column(name = "moment")
    private Date moment;

    @NotNull
    @NotEmpty
    @Column(name = "what")
    private String what;

    @NotNull
    @NotEmpty
    @Column(name = "beforeModification")
    private String beforeModification;

    @NotNull
    @NotEmpty
    @Column(name = "afterModification")
    private String afterModification;

    @JsonIgnore
    @ManyToOne
    private Account account;

}
