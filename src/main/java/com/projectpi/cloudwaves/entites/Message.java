package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;

import java.util.Date;

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_message")
    private int id_message;
    private String contenu;
    private Date date_envoi;
    @ManyToOne
    private Utilisateur utilisateur;
}
