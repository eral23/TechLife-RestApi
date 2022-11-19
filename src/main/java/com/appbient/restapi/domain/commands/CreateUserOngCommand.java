package com.appbient.restapi.domain.commands;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.appbient.restapi.domain.entities.UserOng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class CreateUserOngCommand {
	@TargetAggregateIdentifier
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer role;
    private String locale;
    private String description;
}
