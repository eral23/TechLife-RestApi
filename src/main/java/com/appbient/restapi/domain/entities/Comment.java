package com.appbient.restapi.domain.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//<!--<version>3.0.0-RC1</version>--->
@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity
public class Comment {
    @Id
    @Column(name="id_comment")
    private Integer id;
    private String content;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userOng_id")
    private UserOng ongAuthor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userVolunteer_id")
    private UserVolunteer volunteerAuthor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_publication")
    private Publication publication;
}
