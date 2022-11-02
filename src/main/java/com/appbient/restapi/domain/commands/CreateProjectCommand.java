package com.appbient.restapi.domain.commands;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class CreateProjectCommand {
	@TargetAggregateIdentifier
	private Integer projectId;
	private String name;
	private String description;
	private UserOng userOng;
	private String location;
	private String mission;
	private String functions;
	private String photoUrls;
	private String requirements;
}
