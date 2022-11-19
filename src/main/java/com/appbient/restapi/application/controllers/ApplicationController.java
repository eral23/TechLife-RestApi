package com.appbient.restapi.application.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appbient.restapi.application.controllers.dto.ApplicationCreationDTO;
import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.services.ApplicationCommandService;
import com.appbient.restapi.domain.services.ApplicationQueryService;

@RestController
@RequestMapping(value = "/applications")
public class ApplicationController {
	@Autowired
	private ApplicationCommandService applicationCommandService;
	@Autowired
	private ApplicationQueryService applicationQueryService;
	
	@CrossOrigin
	@PostMapping()
	public CompletableFuture<ResponseEntity<String>> createApplication(@RequestBody ApplicationCreationDTO creationDTO){
		return this.applicationCommandService.createApplication(creationDTO);
	}
	@CrossOrigin
	@DeleteMapping(value="/{id}")
	public CompletableFuture<ResponseEntity<String>> deleteApplication(@PathVariable int id){
		return this.applicationCommandService.deleteApplication(id);
	}
	
	@CrossOrigin
	@PutMapping(value="/{id}")
	public CompletableFuture<ResponseEntity<String>> updateApplication(@PathVariable("id")Integer id, @RequestParam("status") Integer status){
		return this.applicationCommandService.evaluateApplication(id,status);
	}
	
	@CrossOrigin
	@GetMapping(value="/ong/{id}")
	public List<Application> getApplicationsByOng(@PathVariable("id") Integer ongId){
		return this.applicationQueryService.findAllByUserOng(ongId);
	}
	
	@CrossOrigin
	@GetMapping(value="/volunteer/{id}")
	public List<Application> getApplicationsByVolunteer(@PathVariable("id") Integer volunteerId){
		return this.applicationQueryService.findAllByUserVolunteer(volunteerId);
	}
}
