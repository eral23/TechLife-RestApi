package com.appbient.restapi.application.controllers.dto;

import lombok.Value;

@Value
public class PublicationCreationDTO {
	private final String title;
	private final String content;
	private final Integer ong_id;
	private final Integer volunteer_id;
}
