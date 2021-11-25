package com.example.accessingdatamysql.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

}
