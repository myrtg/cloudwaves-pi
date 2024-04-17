package com.cloudwaves.pfeconnect.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="condidature")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_condidature")
    private long id;
    private String statut;
    private int score;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private OffreStage offreStage;
}
