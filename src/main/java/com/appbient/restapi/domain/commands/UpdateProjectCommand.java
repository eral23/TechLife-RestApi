package com.appbient.restapi.domain.commands;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.UserOng;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class UpdateProjectCommand {
    @TargetAggregateIdentifier
    private Integer id;
	private String name;
	private String description;
    private LocalDateTime creationDate;
    private UserOng userOng;
	private String location;
	private String mission;
	private String functions;
	private String photoUrls;
	private String requirements;
}