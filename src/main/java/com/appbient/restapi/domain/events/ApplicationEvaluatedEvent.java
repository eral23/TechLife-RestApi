package com.appbient.restapi.domain.events;

import java.time.LocalDateTime;

import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserVolunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEvaluatedEvent {
    private Integer id;
    private Integer status;
}
