package com.appbient.restapi.application.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.services.PublicationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/foro")
//@Api(value = "Bank Account Commands", description = "Bank Account Commands API")
public class PublicationController {
	private final PublicationService publicationService;
	
	@Autowired
	public PublicationController (PublicationService service) {
		this.publicationService=service;
	}
	
	@PostMapping(value="create-publication")
	public CompletableFuture<Publication> createPublication(@RequestBody Publication publication){
		return this.publicationService.createPublication(publication);
	}
	
	@PostMapping(value="delete-publication")
	public CompletableFuture<Publication> deletePublication(@RequestBody Publication publication){
		return this.publicationService.deletePublication(publication);
	}
}
