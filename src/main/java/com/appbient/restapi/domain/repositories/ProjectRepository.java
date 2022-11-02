package com.appbient.restapi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appbient.restapi.domain.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    @Query(value="select * from project p where p.id_userOng=?1",nativeQuery = true)
    List<Project> findProjectsByUser(int id);
    
    @Query(value="select * from project p where p.name like %:keyword%",nativeQuery = true)
    List<Project> findProjectsByKeyword(String keyword);
}
