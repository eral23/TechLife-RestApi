package com.appbient.restapi.domain.projectors;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.appbient.restapi.domain.commands.CreateCommentCommand;
import com.appbient.restapi.domain.commands.DeleteCommentCommand;
import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.CommentRepository;
import com.appbient.restapi.domain.repositories.PublicationRepository;
import com.appbient.restapi.domain.repositories.UserOngRepository;
import com.appbient.restapi.domain.repositories.UserVolunteerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommentProjection {
	private final CommentRepository commentRepository;
	private final UserOngRepository userOngRepository;
	private final UserVolunteerRepository userVolunteerRepository;
	private final PublicationRepository publicationRepository; 
	
	public void createComment(CreateCommentCommand command) {
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
		
		if(command.getPublication().getId()!=null) {
			Optional<Publication> optionalUserVolunteer= this.publicationRepository.findById(command.getPublication().getId());
			if(!optionalUserVolunteer.isPresent() ) {
				throw new ResourceNotFoundException("La publicacion ingresada no existe en la base de datos");
			}
		}

		if(userType==0) {
			throw new ResourceNotFoundException("El usuario ingresado no existe en la base de datos");
		}
		else if(userType==1) {
			Comment comment=new Comment(
					command.getId(),
					command.getContent(),
					command.getCreationDate(),
					command.getOngAuthor(),
					null,
					command.getPublication()
				);
			commentRepository.save(comment);
		}else if(userType==2){
			Comment comment=new Comment(
					command.getId(),
					command.getContent(),
					command.getCreationDate(),
					null,
					command.getVolunteerAuthor(),
					command.getPublication()
				);
			commentRepository.save(comment);
		}
	}

	public void deleteComment(DeleteCommentCommand command) {
        //System.out.println("----------HGFHFHDHGFGOlASDFa" );
		Optional<Comment> optionalComment= this.commentRepository.findById(command.getId());
		if(optionalComment.isPresent()) {
			Comment comment=optionalComment.get();
			this.commentRepository.delete(comment);
		}else {
			throw new ResourceNotFoundException("El comentario con id " + command.getId() + "No Existe en la bd");
		}
	}
}
