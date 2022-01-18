package com.example.accessingdatamysql.forum;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Boolean hasComment(){
        return this.comments.size()>0;
    }

    public LocalDateTime lastCommentDate() {
            List<Comment> c=this.getComments();
            c.sort((d1,d2) -> d1.getDate().compareTo(d2.getDate()));
        return c.get(c.size()-1).getDate();
    }

    public Forum(String name, LocalDateTime date){
        this.name=name;
        this.creationDate=date;
    }

    public Forum(){
        this.name="";
        this.creationDate=LocalDateTime.now();
    }

}
