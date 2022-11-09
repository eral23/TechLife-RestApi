package com.appbient.restapi.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.repositories.CommentRepository;
import com.appbient.restapi.domain.repositories.PublicationRepository;
@Service
public class PublicationQueryService {
	@Autowired
	PublicationRepository publicationRepository;
	CommentRepository commentRepository;
	public List<Publication> findAll(){
		return this.publicationRepository.findAll();
	}
	public List<Publication> findAllByUser(String userType, int userId){
        System.out.println(userType);
		if(userType.equals("ong")) {
	        System.out.println("HOla2");
			return this.publicationRepository.findPublicationsByOngUser(userId);
		}else if(userType.equals("volunteer")) {
			return this.publicationRepository.findPublicationsByVolunteerUser(userId);
		}else {
			//TODO: arrojar error "tipo de usuario no contemplado"
			return null;
		}
	}
	public Publication findById(Integer id) {
		Optional<Publication>optionalPublication=this.publicationRepository.findById(id);
		if(optionalPublication.isPresent()) {
			return optionalPublication.get();
		}else {
			return null;
		}
	}
	public List<Comment> findCommentsByPublication(int publicationId){
		return this.commentRepository.findCommensByPublication(publicationId);
	}
}
