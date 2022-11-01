package com.appbient.restapi.domain.services;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.appbient.restapi.domain.commands.CreatePublicationCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.entities.Publication;

@Service

public class PublicationService {
	private final CommandGateway commandGateway;
//	
	public PublicationService (CommandGateway commandGateway) {
		this.commandGateway=commandGateway;
	}
	
	public CompletableFuture<Publication> createPublication(Publication publication){
		
		return this.commandGateway.send(new CreatePublicationCommand(
					ServiceUtils.generateUniqueId(),
					publication.getTitle(),
					publication.getContent(),
					publication.getOngAuthor(),
					publication.getVolunteerAuthor(),
					null
				));
	}
	public CompletableFuture<Publication> deletePublication(Publication publication){
		return this.commandGateway.send(new DeletePublicationCommand(
					publication.getId()
				));
	}
}
