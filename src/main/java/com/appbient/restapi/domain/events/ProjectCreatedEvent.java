package com.appbient.restapi.domain.events;

import java.time.LocalDateTime;

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
	private LocalDateTime creationDate;
	private UserOng userOng;
	private String location;
	private String mission;
	private String functions;
	private String photoUrls;
	private String requirements;
}
