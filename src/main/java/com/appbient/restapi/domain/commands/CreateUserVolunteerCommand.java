package com.appbient.restapi.domain.commands;

import java.util.Date;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class CreateUserVolunteerCommand {
	@TargetAggregateIdentifier
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer role;
    
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Integer genre;
    private Integer experience;
    private String dni;
}
