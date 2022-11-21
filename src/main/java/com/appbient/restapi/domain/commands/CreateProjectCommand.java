package com.appbient.restapi.domain.commands;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.UserOng;

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
    private LocalDateTime creationDate;
	private UserOng userOng;
	private String location;
	private double lat;
	private double lng;
	private String mission;
	private String functions;
	private String photoUrls;
	private String requirements;
}
