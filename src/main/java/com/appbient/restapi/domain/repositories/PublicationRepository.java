package com.appbient.restapi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appbient.restapi.domain.entities.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer>{
    @Query(value="select * from publication p where p.id_userOng=?1",nativeQuery = true)
    List<Publication> findPublicationsByOngUser(int id);
    @Query(value="select * from publication p where p.id_userVolunteer=?1",nativeQuery = true)
    List<Publication> findPublicationsByVolunteerUser(int id);
}
