package com.appbient.restapi.application.controllers.dto;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class UserOngCreationDTO {
    private String name;
    private String email;
    private String password;
    
    private String locale;
    private String description;
}
