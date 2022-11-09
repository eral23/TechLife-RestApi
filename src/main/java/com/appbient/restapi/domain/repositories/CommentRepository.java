package com.appbient.restapi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.Project;

public interface CommentRepository  extends JpaRepository<Comment, Integer>{
    @Query(value="select * from comment c where c.id_publication=?1",nativeQuery = true)
    List<Comment> findCommensByPublication(Integer id);
}
