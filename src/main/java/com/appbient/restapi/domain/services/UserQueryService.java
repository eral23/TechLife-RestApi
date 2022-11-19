package com.appbient.restapi.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.UserOngRepository;
import com.appbient.restapi.domain.repositories.UserVolunteerRepository;

@Service
public class UserQueryService {
	@Autowired
	private UserVolunteerRepository userVolunteerRepository;
	@Autowired
	private UserOngRepository userOngRepository;
	
	public UserOng findUserOngByEmail(String email) {
		List<UserOng> users=userOngRepository.findUserByEmail(email);
		
		if(users.size()!=1) {
			throw new ResourceNotFoundException("No se encontro un usuario con ese correo o mas de un usuario tienen el mismo correo");
		}else {
			return users.get(0);
		}
	}
	public UserVolunteer findUserVolunteerByEmail(String email) {
		List<UserVolunteer> users=userVolunteerRepository.findUserByEmail(email);
		
		if(users.size()!=1) {
			throw new ResourceNotFoundException("No se encontro un usuario con ese correo o mas de un usuario tienen el mismo correo");
		}else {
			return users.get(0);
		}
	}
	
}
