package com.appbient.restapi.domain.services;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appbient.restapi.application.controllers.dto.CommentCreationDTO;
import com.appbient.restapi.application.controllers.dto.PublicationCreationDTO;
import com.appbient.restapi.domain.commands.CreateCommentCommand;
import com.appbient.restapi.domain.commands.CreatePublicationCommand;
import com.appbient.restapi.domain.commands.DeleteCommentCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.projectors.CommentProjection;
import com.appbient.restapi.domain.projectors.PublicationProjection;

@Service

public class PublicationCommandService {
	private final CommandGateway commandGateway;
	
 
    private final PublicationProjection publicationProjection;
    private final CommentProjection commentProjection;
//	
	public PublicationCommandService (CommandGateway commandGateway, PublicationProjection publicationProjection, CommentProjection commentProjection) {
		this.commandGateway=commandGateway;
		this.publicationProjection=publicationProjection;
		this.commentProjection=commentProjection;
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
				creationDTO.getCreationDate(),
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
	public CompletableFuture<ResponseEntity<String>> deletePublication(int id){
		DeletePublicationCommand command=new DeletePublicationCommand(id);
		
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
	public CompletableFuture<ResponseEntity<String>> createComment(CommentCreationDTO creationDTO, Integer publicationId){
		if(creationDTO.getOng_id()!= null && creationDTO.getVolunteer_id()!=null) {
			throw new ResourceNotFoundException("Un usuario no puede ser de tipo voluntario y ong a la vez");
		}
		
		if(creationDTO.getOng_id()== null && creationDTO.getVolunteer_id()==null) {
			throw new ResourceNotFoundException("El comentario debe tener un usuario dueño");
		}
		if(publicationId==null) {
			throw new ResourceNotFoundException("El campo publicationId no puede ser null");
		}
		
		CreateCommentCommand command=new CreateCommentCommand(
				ServiceUtils.generateUniqueId(),
				creationDTO.getContent(),
				creationDTO.getCreationDate(),
				new UserOng(creationDTO.getOng_id()),
				new UserVolunteer(creationDTO.getVolunteer_id()),
				new Publication(publicationId)
		);
		
		try {
			this.commentProjection.createComment(command);
		}catch(Exception e) {
			throw e;
		}

		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("Comentario creada con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
	public CompletableFuture<ResponseEntity<String>> deleteComment(int id){
		DeleteCommentCommand command=new DeleteCommentCommand(id);
		
		try {
			this.commentProjection.deleteComment(command);
		}catch(Exception e) {
			throw e;
		}
		
		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("Comentario eliminado con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
}
