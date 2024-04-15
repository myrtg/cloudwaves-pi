package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="offreStage")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class OffreStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_offre")
    private long id;
    private String titre;
    private String description;
    private Date datePublication;
    private Date dateLimite;

    @OneToMany(mappedBy = "offreStage",cascade = CascadeType.ALL)
    private Set<Condidature> condidatures;

    @ManyToMany
    private Set<TestTechnique> testTechniques;
}
