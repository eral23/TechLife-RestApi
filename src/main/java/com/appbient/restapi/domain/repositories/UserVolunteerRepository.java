package com.appbient.restapi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;

public interface UserVolunteerRepository extends JpaRepository<UserVolunteer, Integer>{
    @Query(value="select * from uservolunteer u where u.email like %:email%",nativeQuery = true)
    List<UserVolunteer> findUserByEmail(String email);
}
