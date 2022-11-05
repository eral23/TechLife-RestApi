package com.appbient.restapi.domain.projectors;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.appbient.restapi.domain.commands.CreateProjectCommand;
import com.appbient.restapi.domain.commands.DeleteProjectCommand;
import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.ProjectRepository;
import com.appbient.restapi.domain.repositories.UserOngRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProjectProjection {
	private final ProjectRepository projectRepository;
	private final UserOngRepository userOngRepository;
	//private final UserVolunteerRepository userVolunteerRepository;
	
	public void createProject(CreateProjectCommand command) {
		if(command.getUserOng().getId()!=null) {
			Optional<UserOng> optionalUserOng= this.userOngRepository.findById(command.getUserOng().getId());
			if(!optionalUserOng.isPresent() ) {
				throw new ResourceNotFoundException("El usuario ingresado no existe en la base de datos");
			}
		}else {
			throw new ResourceNotFoundException("El usuario ingresado no existe en la base de datos");
		}
		
		Project project=new Project(
			command.getProjectId(),
			command.getName(),
			command.getDescription(),
			command.getCreationDate(),
			command.getUserOng(),
			command.getLocation(),
			command.getMission(),
			command.getFunctions(),
			command.getPhotoUrls(),
			command.getRequirements(),
			null
		);
		projectRepository.save(project);
	}

	public void deletePublication(DeleteProjectCommand command) {
		Optional<Project> optionalProject= this.projectRepository.findById(command.getProjectId());
		if(optionalProject.isPresent()) {
			Project project=optionalProject.get();
			this.projectRepository.delete(project);
		}else {
			throw new ResourceNotFoundException("El proyecto con id " + command.getProjectId() + " No Existe en la bd");
		}
	}
}