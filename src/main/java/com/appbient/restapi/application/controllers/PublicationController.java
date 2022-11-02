package com.appbient.restapi.application.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appbient.restapi.application.controllers.dto.PublicationCreationDTO;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.services.PublicationCommandService;
import com.appbient.restapi.domain.services.PublicationQueryService;

@RestController
@RequestMapping(value = "/foro")
//@Api(value = "Bank Account Commands", description = "Bank Account Commands API")
public class PublicationController {
	@Autowired
	private PublicationCommandService publicationCommandService;
	@Autowired
	private PublicationQueryService publicationQueryService;

	@PostMapping(value="create-publication")
	public CompletableFuture<ResponseEntity<String>> createPublication(@RequestBody PublicationCreationDTO creationDTO){
		return this.publicationCommandService.createPublication(creationDTO);
	}
	
	@PostMapping(value="delete-publication")
	public CompletableFuture<ResponseEntity<String>> deletePublication(@RequestBody Publication publication){
		return this.publicationCommandService.deletePublication(publication);
	}
	@GetMapping(value="publications")
	public List<Publication> getPublicationByUser(){
		return this.publicationQueryService.findAll();
	}
	@GetMapping(value="publications/{type}/{id}")
	public List<Publication> getPublicationByUser(@PathVariable("type")String userType,@PathVariable("id")int userId){
		return this.publicationQueryService.findAllByUser(userType,userId);
	}
}

//"title":"Consejos para reducir el uso de plasticos",
//"content":"contenido de la publicacion",
//"ong_id":null,
//"volunteer_id":4