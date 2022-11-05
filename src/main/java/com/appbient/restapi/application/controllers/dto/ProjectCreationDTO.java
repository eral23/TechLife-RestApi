package com.appbient.restapi.application.controllers.dto;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class ProjectCreationDTO {
	private String name;
	private String description;
	private LocalDateTime creationDate;
	private Integer ong_id;
	private String location;
	private String mission;
	private String functions;
	private String photo_urls;
	private String requirements;
}
