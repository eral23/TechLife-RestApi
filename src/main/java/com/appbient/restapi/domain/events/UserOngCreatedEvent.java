package com.appbient.restapi.domain.events;

import java.time.LocalDateTime;

import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOngCreatedEvent {
    private Integer id;
    private String name;
    private String email;
    private String password;
    
    private String locale;
    private String description;
}
