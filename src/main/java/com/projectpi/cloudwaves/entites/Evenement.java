package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Evenement")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evenement")
    private long id;
    private String titre;
    private String description;
    private Date DateDebut;
    private Date DateFin;
    private int nbPlace;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "evenement",cascade = CascadeType.ALL)
    private Set<Inscription> inscriptionSet;
}
