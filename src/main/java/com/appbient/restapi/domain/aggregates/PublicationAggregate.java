package com.appbient.restapi.domain.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.appbient.restapi.domain.commands.CreatePublicationCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.events.PublicationCreatedEvent;
import com.appbient.restapi.domain.events.PublicationDeletedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;
@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate // 1
public class PublicationAggregate {
    @AggregateIdentifier // 2
    private Integer id;
//    private String title;
//    private String content;
//    private UserOng ongAuthor;
//    private UserVolunteer volunteerAuthor;
//    private List<Comment> comments;
    @CommandHandler 
    public PublicationAggregate(CreatePublicationCommand command) {
    	AggregateLifecycle.apply(
    			new PublicationCreatedEvent(
    					command.getPublicationId(),
    					command.getTitle(),
    					command.getContent(),
    					command.getOngAuthor(),
    					command.getVolunteerAuthor(),
    					command.getComments()
    					)
    			);
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
    }

    @EventSourcingHandler
    public void on(PublicationDeletedEvent event) {
    	//markDeleted();
    }
    
    
}
