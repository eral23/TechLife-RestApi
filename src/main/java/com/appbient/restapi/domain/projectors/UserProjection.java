package com.appbient.restapi.domain.projectors;

import java.util.List;

import org.springframework.stereotype.Component;

import com.appbient.restapi.domain.commands.CreateUserOngCommand;
import com.appbient.restapi.domain.commands.CreateUserVolunteerCommand;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.UserOngRepository;
import com.appbient.restapi.domain.repositories.UserVolunteerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserProjection {
	private final UserOngRepository userOngRepository;
	private final UserVolunteerRepository userVolunteerRepository;
	
	public void createUserOng(CreateUserOngCommand command) {
		if(command.getEmail()!=null) {
			List<UserOng> users= this.userOngRepository.findUserByEmail(command.getEmail());
			if(users.size()>0) {
				throw new ResourceNotFoundException("El correo ingresado ya esta en uso");
			}
		}else {
			throw new ResourceNotFoundException("El correo es obligatorio");
		}
		
		UserOng userOng=new UserOng(
			command.getId(),
			command.getName(),
			command.getEmail(),
			command.getPassword(),
			command.getRole(),
			command.getLocale(),
			command.getDescription()
		);
		userOngRepository.save(userOng);
	}
	public void createUserVolunteer(CreateUserVolunteerCommand command) {
		if(command.getEmail()!=null) {
			List<UserVolunteer> users= this.userVolunteerRepository.findUserByEmail(command.getEmail());
			if(users.size()>0) {
				throw new ResourceNotFoundException("El correo ingresado ya esta en uso");
			}
		}else {
			throw new ResourceNotFoundException("El correo es obligatorio");
		}
		
		UserVolunteer userVolunteer=new UserVolunteer(
			command.getId(),
			command.getName(),
			command.getEmail(),
			command.getPassword(),
			command.getRole(),
			command.getFirstName(),
			command.getLastName(),
			command.getAge(),
			command.getGenre(),
			command.getExperience(),
			command.getDni()
		);
		userVolunteerRepository.save(userVolunteer);
	}
	
}
