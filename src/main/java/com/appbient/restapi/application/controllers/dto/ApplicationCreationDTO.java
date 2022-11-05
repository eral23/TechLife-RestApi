package com.appbient.restapi.application.controllers.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
@Value
public class ApplicationCreationDTO {
    private final LocalDateTime creation_date;
	private final Integer project_id;
	private final Integer user_volunteer_id;
}
