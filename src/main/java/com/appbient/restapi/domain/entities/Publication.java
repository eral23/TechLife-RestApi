package com.appbient.restapi.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity
public class Publication {
    @Id
    @Column(name="id_publication")
    private Integer id;
    private String title;
    private String content;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_userOng")
    private UserOng ongAuthor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_userVolunteer")
    private UserVolunteer volunteerAuthor;
    @OneToMany(mappedBy = "publication")
    private List<Comment> comments;
    
    
}
