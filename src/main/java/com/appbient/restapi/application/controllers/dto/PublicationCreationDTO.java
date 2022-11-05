package com.appbient.restapi.application.controllers.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class PublicationCreationDTO {
	private final String title;
	private final String content;
	private final Integer ong_id;
	private final Integer volunteer_id;
    @JsonProperty("creation_date")
	private final LocalDateTime creationDate;
}
