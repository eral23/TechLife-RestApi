package com.appbient.restapi.domain.services;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appbient.restapi.application.controllers.dto.PublicationCreationDTO;
import com.appbient.restapi.domain.commands.CreatePublicationCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.projectors.PublicationProjection;

@Service

public class PublicationCommandService {
	private final CommandGateway commandGateway;
	
 
    private final PublicationProjection publicationProjection;
//	
	public PublicationCommandService (CommandGateway commandGateway, PublicationProjection publicationProjection) {
		this.commandGateway=commandGateway;
		this.publicationProjection=publicationProjection;
	}
	
	public CompletableFuture<ResponseEntity<String>> createPublication(PublicationCreationDTO creationDTO){
		if(creationDTO.getOng_id()!= null && creationDTO.getVolunteer_id()!=null) {
			throw new ResourceNotFoundException("Un usuario no puede ser de tipo voluntario y ong a la vez");
		}
		
		if(creationDTO.getOng_id()== null && creationDTO.getVolunteer_id()==null) {
			throw new ResourceNotFoundException("La publicacion debe tener un usuario dueño");
		}
		
		CreatePublicationCommand command=new CreatePublicationCommand(
				ServiceUtils.generateUniqueId(),
				creationDTO.getTitle(),
				creationDTO.getContent(),
				new UserOng(creationDTO.getOng_id()),
				new UserVolunteer(creationDTO.getVolunteer_id()),
				null
		);
		
		try {
			this.publicationProjection.createPublication(command);
		}catch(Exception e) {
			throw e;
		}

		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("publicacion creada con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
	public CompletableFuture<ResponseEntity<String>> deletePublication(Publication publication){
		DeletePublicationCommand command=new DeletePublicationCommand(
				publication.getId()
			);
		try {
			this.publicationProjection.deletePublication(command);
		}catch(Exception e) {
			throw e;
		}
		
		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("publicacion eliminada con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}

}
