package com.example.accessingdatamysql.forum;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.comment.Comment;
import com.example.accessingdatamysql.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Forum extends BaseEntity {

    @Size(min = 3, max = 50)
    @NotEmpty
    @Column(name = "name", unique = true)
    private String name;

    @NotEmpty
    private LocalDate creationDate;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

}
