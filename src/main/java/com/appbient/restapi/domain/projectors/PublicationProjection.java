package com.appbient.restapi.domain.projectors;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.events.PublicationCreatedEvent;
import com.appbient.restapi.domain.events.PublicationDeletedEvent;
import com.appbient.restapi.domain.repositories.PublicationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class PublicationProjection {
	private final PublicationRepository repository;
	
	@EventHandler
	public void on(PublicationCreatedEvent event) {
		Publication publication=new Publication(
					event.getId(),
					event.getTitle(),
					event.getContent(),
					event.getOngAuthor(),
					event.getVolunteerAuthor(),
					event.getComments()
				);
		repository.save(publication);
	}
	@EventHandler
	public void on(PublicationDeletedEvent event) {
        System.out.println("----------HGFHFHDHGFGOlASDFa" );
		Optional<Publication> optionalPublication= this.repository.findById(event.getId());
		if(optionalPublication.isPresent()) {
			Publication publication=optionalPublication.get();
			this.repository.delete(publication);
		}
		//TODO: arrojar excepcion en caso no se encuentre publicacion
	}
}
