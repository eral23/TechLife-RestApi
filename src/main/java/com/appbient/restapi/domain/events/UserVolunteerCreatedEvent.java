package com.appbient.restapi.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVolunteerCreatedEvent {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer role;
    
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer genre;
    private Integer experience;
    private String dni;
}
