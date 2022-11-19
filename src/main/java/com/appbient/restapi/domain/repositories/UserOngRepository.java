package com.appbient.restapi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;

public interface UserOngRepository extends JpaRepository<UserOng, Integer>{
    @Query(value="select * from userong u where u.email like %:email%",nativeQuery = true)
    List<UserOng> findUserByEmail(String email);
}
