package com.appbient.restapi.application.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appbient.restapi.application.controllers.dto.ProjectCreationDTO;
import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.services.ProjectCommandService;
import com.appbient.restapi.domain.services.ProjectQueryService;

@RestController
@RequestMapping(value = "/projects")
//@Api(value = "Bank Account Commands", description = "Bank Account Commands API")
public class ProjectController {
	@Autowired
	private ProjectCommandService projectCommandService;
	@Autowired
	private ProjectQueryService publicationQueryService;

	@PostMapping()
	public CompletableFuture<ResponseEntity<String>> createProject(@RequestBody ProjectCreationDTO creationDTO){
		return this.projectCommandService.createProject(creationDTO);
	}
	
	@DeleteMapping(value="/{id}")
	public CompletableFuture<ResponseEntity<String>> deleteProject(@PathVariable int id){
		return this.projectCommandService.deleteProject(id);
	}
	@GetMapping
	public List<Project> getAllProjects(){
		return this.publicationQueryService.findAll();
	}
	@GetMapping(value="/{id}")
	public Project getProjectById(@PathVariable("id")int id){
		return this.publicationQueryService.findById(id);
	}
	@GetMapping(value="/user/{id}")
	public List<Project> getProjectByUser(@PathVariable("id")int userId){
		return this.publicationQueryService.findByUser(userId);
	}
	@GetMapping(value="/search")
	public List<Project> getProjectByUser(@RequestParam("keyword")String keyword){
		return this.publicationQueryService.findByKeyword(keyword);
	}
}
