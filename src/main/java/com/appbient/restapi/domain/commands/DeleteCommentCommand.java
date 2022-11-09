package com.appbient.restapi.domain.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class DeleteCommentCommand {
	@TargetAggregateIdentifier
	private Integer id;
}
