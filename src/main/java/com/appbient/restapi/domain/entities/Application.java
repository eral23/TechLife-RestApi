package com.appbient.restapi.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity
public class Application {
    @Id
    @Column(name="id_application")
	private Integer id;
	@ManyToOne
	@JoinColumn(name="id_project")
	private Project project;
	@ManyToOne
	@JoinColumn(name="id_userVolunteer")
	private UserVolunteer applicant;
}
