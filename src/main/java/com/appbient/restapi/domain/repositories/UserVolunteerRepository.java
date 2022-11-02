package com.appbient.restapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appbient.restapi.domain.entities.UserVolunteer;
@Repository
public interface UserVolunteerRepository extends JpaRepository<UserVolunteer, Integer>{

}
