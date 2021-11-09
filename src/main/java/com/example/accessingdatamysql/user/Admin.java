package com.example.accessingdatamysql.user;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Admin") // This tells Hibernate to make a table out of this class
public class Admin extends Account {

}