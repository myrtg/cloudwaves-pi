package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;

import java.util.Set;

public class SousCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sous_categorie")
    private int id_sous_categorie;
    private String nom_souscategorie;
    @OneToMany(mappedBy = "categorie")
    private Set<Categorie> categories;

}
