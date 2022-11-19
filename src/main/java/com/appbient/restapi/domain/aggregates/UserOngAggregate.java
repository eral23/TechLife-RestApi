package com.appbient.restapi.domain.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.appbient.restapi.domain.commands.CreateUserOngCommand;
import com.appbient.restapi.domain.events.ProjectCreatedEvent;
import com.appbient.restapi.domain.events.UserOngCreatedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate // 1
public class UserOngAggregate {
    @AggregateIdentifier // 2
    private Integer id;
    
    @CommandHandler 
    public UserOngAggregate(CreateUserOngCommand command) {
    	UserOngCreatedEvent event=new UserOngCreatedEvent(
    		    command.getId(),
    		    command.getName(),
    		    command.getEmail(),
    		    command.getPassword(),
    		    command.getLocale(),
    		    command.getDescription()
				);
    	
    	AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(UserOngCreatedEvent event) {
    	this.id=event.getId();
    }
}
