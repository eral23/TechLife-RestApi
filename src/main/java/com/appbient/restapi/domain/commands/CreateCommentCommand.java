package com.appbient.restapi.domain.commands;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class CreateCommentCommand {
	@TargetAggregateIdentifier
	private Integer id;
    private String content;
    private LocalDateTime creationDate;
    private UserOng ongAuthor;
    private UserVolunteer volunteerAuthor;
    private Publication publication;
}
