package com.appbient.restapi.application.controllers.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.Value;

@Value
public class CommentCreationDTO {
    private final String content;
    @Column(name="creation_date")
    private final LocalDateTime creationDate;
	private final Integer ong_id;
	private final Integer volunteer_id;
}
