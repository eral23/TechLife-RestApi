package com.appbient.restapi.domain.projectors;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.appbient.restapi.domain.commands.CreatePublicationCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.events.PublicationCreatedEvent;
import com.appbient.restapi.domain.events.PublicationDeletedEvent;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.PublicationRepository;
import com.appbient.restapi.domain.repositories.UserOngRepository;
import com.appbient.restapi.domain.repositories.UserVolunteerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class PublicationProjection {
	private final PublicationRepository publicationRepository;
	private final UserOngRepository userOngRepository;
	private final UserVolunteerRepository userVolunteerRepository;
	
	public void createPublication(CreatePublicationCommand command) {
		int userType=0;
		if(command.getOngAuthor().getId()!=null) {
			Optional<UserOng> optionalUserOng= this.userOngRepository.findById(command.getOngAuthor().getId());
			if(optionalUserOng.isPresent() ) {
				userType=1;
			}
		}
		if(command.getVolunteerAuthor().getId()!=null) {
			Optional<UserVolunteer> optionalUserVolunteer= this.userVolunteerRepository.findById(command.getVolunteerAuthor().getId());
			if(optionalUserVolunteer.isPresent() ) {
				userType=2;

			}
		}


		if(userType==0) {
			throw new ResourceNotFoundException("El usuario ingresado no existe en la base de datos");
		}
		else if(userType==1) {
			Publication publication=new Publication(
					command.getPublicationId(),
					command.getTitle(),
					command.getContent(),
					command.getCreationDate(),
					command.getOngAuthor(),
					null,
					command.getComments()
				);
			publicationRepository.save(publication);
		}else if(userType==2){
			Publication publication=new Publication(
					command.getPublicationId(),
					command.getTitle(),
					command.getContent(),
					command.getCreationDate(),
					null,
					command.getVolunteerAuthor(),
					command.getComments()
				);
			publicationRepository.save(publication);
		}
	}

	public void deletePublication(DeletePublicationCommand command) {
        //System.out.println("----------HGFHFHDHGFGOlASDFa" );
		Optional<Publication> optionalPublication= this.publicationRepository.findById(command.getPublicationId());
		if(optionalPublication.isPresent()) {
			Publication publication=optionalPublication.get();
			this.publicationRepository.delete(publication);
		}else {
			throw new ResourceNotFoundException("La publicacion con id " + command.getPublicationId() + "No Existe en la bd");
		}
	}
}
