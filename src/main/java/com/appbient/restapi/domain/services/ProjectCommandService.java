package com.appbient.restapi.domain.services;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appbient.restapi.application.controllers.dto.ProjectCreationDTO;
import com.appbient.restapi.domain.commands.CreateProjectCommand;
import com.appbient.restapi.domain.commands.DeleteProjectCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.projectors.ProjectProjection;

@Service

public class ProjectCommandService {
	private final CommandGateway commandGateway;
	
 
    private final ProjectProjection projectProjection;
//	
	public ProjectCommandService (CommandGateway commandGateway, ProjectProjection projectProjection) {
		this.commandGateway=commandGateway;
		this.projectProjection=projectProjection;
	}
	
	public CompletableFuture<ResponseEntity<String>> createProject(ProjectCreationDTO creationDTO){
		if(creationDTO.getOng_id()== null) {
			throw new ResourceNotFoundException("La proyecto debe tener un usuario dueño");
		}
		
		CreateProjectCommand command=new CreateProjectCommand(
				ServiceUtils.generateUniqueId(),
				creationDTO.getName(),
				creationDTO.getDescription(),
				new UserOng(creationDTO.getOng_id()),
				creationDTO.getLocation(),
				creationDTO.getMission(),
				creationDTO.getFunctions(),
				creationDTO.getPhoto_urls(),
				creationDTO.getRequirements()
		);
		
		try {
			this.projectProjection.createProject(command);
		}catch(Exception e) {
			throw e;
		}

		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("Projecto creado con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
	public CompletableFuture<ResponseEntity<String>> deleteProject(Integer id){
		DeleteProjectCommand command=new DeleteProjectCommand(id);
		try {
			this.projectProjection.deletePublication(command);
		}catch(Exception e) {
			throw e;
		}
		
		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("publicacion eliminada con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}

}
