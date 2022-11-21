package com.appbient.restapi.domain.entities;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity
public class UserVolunteer {
    @Id
    @Column(name="id_userVolunteer")
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
    
    @OneToMany(mappedBy = "volunteerAuthor",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Publication> publications;
    @OneToMany(mappedBy = "volunteerAuthor",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;
    @OneToMany(mappedBy = "applicant",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Application> applications;
    
    public UserVolunteer(Integer id) {
    	this.id=id;
    }
    public UserVolunteer(Integer id, String name, String email, String password,Integer role, String firstName, String lastName, Date birthDate, Integer genre, Integer experience, String dni) {
    	this.id=id;
    	this.name=name;
    	this.email=email;
    	this.password=password;
    	this.role=role;
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.birthDate=birthDate;
    	this.genre=genre;
    	this.experience=experience;
    	this.dni=dni;
    }
}
