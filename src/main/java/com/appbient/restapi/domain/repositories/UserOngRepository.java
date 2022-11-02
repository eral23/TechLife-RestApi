package com.appbient.restapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
@Repository
public interface UserOngRepository extends JpaRepository<UserOng, Integer>{

}
