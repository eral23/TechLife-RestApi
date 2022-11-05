package com.appbient.restapi.domain.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.appbient.restapi.domain.commands.CreateApplicationCommand;
import com.appbient.restapi.domain.commands.DeleteApplicationCommand;
import com.appbient.restapi.domain.commands.EvaluateApplicationCommand;
import com.appbient.restapi.domain.events.ApplicationCreatedEvent;
import com.appbient.restapi.domain.events.ApplicationDeletedEvent;
import com.appbient.restapi.domain.events.ApplicationEvaluatedEvent;
import com.appbient.restapi.domain.events.ProjectDeletedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate // 1
public class ApplicationAggregate {
    @AggregateIdentifier // 2
    private Integer id;
    private Integer status;
    @CommandHandler 
    public ApplicationAggregate(CreateApplicationCommand command) {
    	ApplicationCreatedEvent event=new ApplicationCreatedEvent(
				command.getId(),
				command.getStatus(),
				command.getCreationDate(),
				command.getProject(),
				command.getApplicant()
				);
    	
    	AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeleteApplicationCommand command) {
        AggregateLifecycle.apply(
                new ApplicationDeletedEvent(
                        command.getId()
                )
        );
    }
    @CommandHandler
    public void handle(EvaluateApplicationCommand command) {
    	AggregateLifecycle.apply(
    			new ApplicationEvaluatedEvent(
    					command.getId(),
    					command.getStatus()
    					)
    			);
    }
    @EventSourcingHandler
    public void on(ApplicationCreatedEvent event) {
    	this.id=event.getId();
    }
    @EventSourcingHandler
    public void on(ApplicationEvaluatedEvent event) {
    	this.status=event.getStatus();
    }
    @EventSourcingHandler
    public void on(ProjectDeletedEvent event) {
    	markDeleted();
    }
    
}