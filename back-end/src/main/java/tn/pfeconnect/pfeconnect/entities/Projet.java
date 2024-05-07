package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Projets")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProjet", nullable = false)
    private Long id;
    private String titre;

    private String description;

    private String fichier_rapport;

    private String fichier_code_source;
}
