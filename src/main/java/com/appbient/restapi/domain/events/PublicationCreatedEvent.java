package com.appbient.restapi.domain.events;

import java.util.List;

import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.entities.UserOng;
import com.appbient.restapi.domain.entities.UserVolunteer;


import lombok.Data;

@Data // Lombok
public class PublicationCreatedEvent {
	private final Integer id;
    private final String title;
    private final String content;
    private final UserOng ongAuthor;
    private final UserVolunteer volunteerAuthor;
    private final List<Comment> comments;
}
