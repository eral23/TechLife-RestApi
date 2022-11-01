package com.appbient.restapi.domain.commands;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class CreatePublicationCommand {
	@TargetAggregateIdentifier
	private Integer publicationId;
    private String title;
    private String content;
    private UserOng ongAuthor;
    private UserVolunteer volunteerAuthor;
    private List<Comment> comments;
}
