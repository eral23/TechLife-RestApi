package com.appbient.restapi.domain.services;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appbient.restapi.application.controllers.dto.UserOngCreationDTO;
import com.appbient.restapi.application.controllers.dto.UserVolunteerCreationDTO;
import com.appbient.restapi.domain.commands.CreateUserOngCommand;
import com.appbient.restapi.domain.commands.CreateUserVolunteerCommand;
import com.appbient.restapi.domain.projectors.UserProjection;

@Service
public class UserCommandService {
	private final CommandGateway commandGateway;
	private final UserProjection userProjection;
	
	public UserCommandService (CommandGateway commandGateway, UserProjection userProjection) {
		this.commandGateway=commandGateway;
		this.userProjection=userProjection;
	}
	public CompletableFuture<ResponseEntity<String>> createUserOng(UserOngCreationDTO userOngDTO){
		CreateUserOngCommand command= new CreateUserOngCommand(
				ServiceUtils.generateUniqueId(),
					userOngDTO.getName(),
					userOngDTO.getEmail(),
					"{noop}" + userOngDTO.getPassword(),
					0,
					userOngDTO.getLocale(),
					userOngDTO.getDescription()
				);
		
		try {
			this.userProjection.createUserOng(command);
		}catch(Exception e) {
			throw e;
		}
		
		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("Usuario ong registrado con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
	public CompletableFuture<ResponseEntity<String>> createUserVolunteer(UserVolunteerCreationDTO userVolunteerDTO){
		CreateUserVolunteerCommand command= new CreateUserVolunteerCommand(
				ServiceUtils.generateUniqueId(),
			    userVolunteerDTO.getName(),
			    userVolunteerDTO.getEmail(),
			    "{noop}"+userVolunteerDTO.getPassword(),
			    1,
			    userVolunteerDTO.getFirstName(),
			    userVolunteerDTO.getLastName(),
			    userVolunteerDTO.getAge(),
			    userVolunteerDTO.getGenre(),
			    userVolunteerDTO.getExperience(),
			    userVolunteerDTO.getDni()
				);
		
		try {
			this.userProjection.createUserVolunteer(command);
		}catch(Exception e) {
			throw e;
		}
		
		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("Usuario voluntario registrado con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
}
