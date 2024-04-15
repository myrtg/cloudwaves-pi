package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;

public class ModeleRapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modele_rapport")
    private int id_modele_rapport;
    private String titre;
    private String description;
    private String fichier_module;
    @OneToOne(mappedBy = "categorie")
    private Categorie categorie;
}
