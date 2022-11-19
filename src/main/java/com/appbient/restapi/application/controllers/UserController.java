package com.appbient.restapi.application.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appbient.restapi.application.controllers.dto.UserOngCreationDTO;
import com.appbient.restapi.application.controllers.dto.UserVolunteerCreationDTO;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.services.UserCommandService;
import com.appbient.restapi.domain.services.UserQueryService;

@RestController
public class UserController {
	@Autowired
	private UserCommandService userCommandService;
	@Autowired
	private UserQueryService userQueryService;
	
	@PostMapping(value="/volunteer/signup")
	public CompletableFuture<ResponseEntity<String>> createUserVolunteer(@RequestBody UserVolunteerCreationDTO creationDTO){
		return this.userCommandService.createUserVolunteer(creationDTO);
	}
	@PostMapping(value="/ong/signup")
	public CompletableFuture<ResponseEntity<String>> createUserOng(@RequestBody UserOngCreationDTO creationDTO){
		return this.userCommandService.createUserOng(creationDTO);
	}
	
	
	@CrossOrigin
	@GetMapping(value="/volunteer")
	public UserVolunteer getUserVolunteerByEmail(@RequestParam("email")String email){
		return this.userQueryService.findUserVolunteerByEmail(email);
	}
	
	@CrossOrigin
	@GetMapping(value="/ong")
	public UserOng getUserOngByEmail(@RequestParam("email")String email){
		return this.userQueryService.findUserOngByEmail(email);
	}
}
