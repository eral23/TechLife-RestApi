package com.appbient.restapi.domain.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.appbient.restapi.domain.commands.CreateCommentCommand;
import com.appbient.restapi.domain.commands.DeleteCommentCommand;
import com.appbient.restapi.domain.events.CommentCreatedEvent;
import com.appbient.restapi.domain.events.CommentDeletedEvent;
import com.appbient.restapi.domain.events.PublicationDeletedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate // 1
public class CommentAggregate {
    @AggregateIdentifier // 2
    private Integer id;
    
    @CommandHandler
    public CommentAggregate(CreateCommentCommand command) {
    	CommentCreatedEvent event=new CommentCreatedEvent(
				command.getId(),
				command.getContent(),
				command.getCreationDate(),
				command.getOngAuthor(),
				command.getVolunteerAuthor(),
				command.getPublication()
				);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeleteCommentCommand command) {
        AggregateLifecycle.apply(
                new CommentDeletedEvent(
                        command.getId()
                )
        );
    }
    @EventSourcingHandler
    public void on(CommentCreatedEvent event) {
    	this.id=event.getId();
    }
    @EventSourcingHandler
    public void on(CommentDeletedEvent event) {
    	markDeleted();
    }
}
