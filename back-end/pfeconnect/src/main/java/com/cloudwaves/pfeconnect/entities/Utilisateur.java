package com.cloudwaves.pfeconnect.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String motdepasse;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private Set<Condidature> condidatures;

    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Evenement> evenements;


}
