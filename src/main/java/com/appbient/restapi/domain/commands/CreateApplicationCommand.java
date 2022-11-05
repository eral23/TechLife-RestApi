package com.appbient.restapi.domain.commands;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class CreateApplicationCommand {
	@TargetAggregateIdentifier
	private Integer id;
    private Integer status;
    private LocalDateTime creationDate;
	private Project project;
	private UserVolunteer applicant;
}
