package com.appbient.restapi.domain.events;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.entities.UserOng;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreatedEvent {
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
