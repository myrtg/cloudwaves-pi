package com.projectpi.cloudwaves.entites;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private long idUser;
    private String nom;
    private String prenom;
    private String email;
    private String motdepasse;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "Utilisateur",cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"email", "motdepasse", "role","evenements","candidatures"})
    private Set<Candidature> candidatures;

    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private Set<Evenement> evenements;


}
