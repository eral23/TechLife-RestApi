package com.appbient.restapi.domain.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.appbient.restapi.domain.commands.CreateCommentCommand;
import com.appbient.restapi.domain.commands.CreatePublicationCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.entities.Comment;
import com.appbient.restapi.domain.events.CommentCreatedEvent;
import com.appbient.restapi.domain.events.PublicationCreatedEvent;
import com.appbient.restapi.domain.events.PublicationDeletedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate
public class PublicationAggregate {
    @AggregateIdentifier
    private Integer id;
    private String title;
    
//    private String content;
//    private UserOng ongAuthor;
//    private UserVolunteer volunteerAuthor;
   private List<Comment> comments;
    @CommandHandler 
    public PublicationAggregate(CreatePublicationCommand command) {
    	PublicationCreatedEvent event=new PublicationCreatedEvent(
				command.getPublicationId(),
				command.getTitle(),
				command.getContent(),
				command.getCreationDate(),
				command.getOngAuthor(),
				command.getVolunteerAuthor(),
				command.getComments()
				);
		
    	AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeletePublicationCommand command) {
        AggregateLifecycle.apply(
                new PublicationDeletedEvent(
                        command.getPublicationId()
                )
        );
    }

    @EventSourcingHandler
    public void on(PublicationCreatedEvent event) {
    	this.id=event.getId();
    	this.title=event.getTitle();
    }
//    @EventSourcingHandler
//    public void on(CommentCreatedEvent event) {
//    	
//    	this.comments.add(new Comment(
//    			event.getId(),
//    			event.getContent(),
//    			event.getCreationDate(),
//    			event.getOngAuthor(),
//    			event.getVolunteerAuthor(),
//    			event.getPublication()
//    			));
//    }
    @EventSourcingHandler
    public void on(PublicationDeletedEvent event) {
    	markDeleted();
    }
    
}
