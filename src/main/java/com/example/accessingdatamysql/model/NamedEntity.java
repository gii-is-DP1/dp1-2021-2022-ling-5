package com.example.accessingdatamysql.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class NamedEntity extends BaseEntity {

  @Size(min = 3, max = 50)
  @NotEmpty
  @Column(name = "name")
  protected String name;

}