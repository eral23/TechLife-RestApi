package com.appbient.restapi.domain.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.appbient.restapi.domain.commands.CreateProjectCommand;
import com.appbient.restapi.domain.commands.DeleteProjectCommand;
import com.appbient.restapi.domain.commands.DeletePublicationCommand;
import com.appbient.restapi.domain.events.ProjectCreatedEvent;
import com.appbient.restapi.domain.events.ProjectDeletedEvent;
import com.appbient.restapi.domain.events.PublicationCreatedEvent;
import com.appbient.restapi.domain.events.PublicationDeletedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate // 1
public class ProjectAggregate {
    @AggregateIdentifier // 2
    private Integer id;
    

    @CommandHandler 
    public ProjectAggregate(CreateProjectCommand command) {
    	ProjectCreatedEvent event=new ProjectCreatedEvent(
				command.getProjectId(),
				command.getName(),
				command.getDescription(),
				command.getUserOng(),
				command.getLocation(),
				command.getMission(),
				command.getFunctions(),
				command.getPhotoUrls(),
				command.getRequirements()
				);
    	
    	AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeleteProjectCommand command) {
        AggregateLifecycle.apply(
                new PublicationDeletedEvent(
                        command.getProjectId()
                )
        );
    }
    @EventSourcingHandler
    public void on(ProjectCreatedEvent event) {
    	this.id=event.getProjectId();
    }

    @EventSourcingHandler
    public void on(ProjectDeletedEvent event) {
    	markDeleted();
    }
    
}