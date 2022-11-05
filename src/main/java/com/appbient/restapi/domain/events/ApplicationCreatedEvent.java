package com.appbient.restapi.domain.events;

import java.time.LocalDateTime;

import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreatedEvent {
	private Integer id;
    private Integer status;
    private LocalDateTime creationDate;
	private Project project;
	private UserVolunteer applicant;
}
