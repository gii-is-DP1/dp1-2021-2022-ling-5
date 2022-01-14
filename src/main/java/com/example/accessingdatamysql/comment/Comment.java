package com.example.accessingdatamysql.comment;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.example.accessingdatamysql.forum.Forum;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Comment extends BaseEntity {

    @NotEmpty
    private String text;

    @NotEmpty
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @NotEmpty
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @ManyToOne
    @NotEmpty
    @JoinColumn(name = "account_id")
    private Account user;
}
