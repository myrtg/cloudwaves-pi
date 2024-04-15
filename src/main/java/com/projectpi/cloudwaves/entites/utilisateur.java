package com.projectpi.cloudwaves.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class utilisateur {
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
}
