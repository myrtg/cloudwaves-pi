package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Forum")


public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum")
    private long id_forum;
    private String contenu;
    private Date date_publication;
    @ManyToOne
    private Utilisateur utilisateur;




}
