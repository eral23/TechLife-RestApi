package com.appbient.restapi.domain.events;

import java.time.LocalDateTime;

import com.appbient.restapi.domain.entities.UserOng;

import lombok.Data;
@Data
public class ProjectUpdatedEvent {
    private final Integer id;
	private final String name;
	private final String description;
    private final LocalDateTime creationDate;
    private final UserOng userOng;
	private final String location;
	private final double lat;
	private final double lng;
	private final String mission;
	private final String functions;
	private final String photoUrls;
	private final String requirements;
}
