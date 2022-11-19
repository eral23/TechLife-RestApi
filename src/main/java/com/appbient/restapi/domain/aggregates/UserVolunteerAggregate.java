package com.appbient.restapi.domain.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.appbient.restapi.domain.commands.CreateUserVolunteerCommand;
import com.appbient.restapi.domain.events.UserVolunteerCreatedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate // 1
public class UserVolunteerAggregate {
    @AggregateIdentifier // 2
    private Integer id;
    
    @CommandHandler 
    public UserVolunteerAggregate(CreateUserVolunteerCommand command) {
    	UserVolunteerCreatedEvent event=new UserVolunteerCreatedEvent(
    		    command.getId(),
    		    command.getName(),
    		    command.getEmail(),
    		    command.getPassword(),
    		    command.getRole(),
    		    command.getFirstName(),
    		    command.getLastName(),
    		    command.getAge(),
    		    command.getGenre(),
    		    command.getExperience(),
    		    command.getDni()
				);
    	
    	AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(UserVolunteerCreatedEvent event) {
    	this.id=event.getId();
    }
}