package com.appbient.restapi.domain.commands;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class DeletePublicationCommand {
	@TargetAggregateIdentifier
	private Integer publicationId;
}
