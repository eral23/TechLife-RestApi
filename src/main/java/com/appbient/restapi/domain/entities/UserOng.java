package com.appbient.restapi.domain.entities;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class UserOng {
    @Id
    @Column(name="id_userOng")
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "ongAuthor", fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Publication> publications;
    @OneToMany(mappedBy = "ongAuthor",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;
    
    public UserOng(Integer id) {
    	this.id=id;
    }
}