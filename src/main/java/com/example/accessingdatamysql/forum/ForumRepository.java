package com.example.accessingdatamysql.forum;

import org.springframework.data.repository.CrudRepository;

public interface ForumRepository extends CrudRepository<Forum, Long> {

}
