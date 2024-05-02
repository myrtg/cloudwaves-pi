package com.projectpi.cloudwaves.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
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
    private long idOffre;
    private String titre;
    private String description;
    private Date datePublication;
    private Date dateLimite;
    @Enumerated(EnumType.STRING)
    private Domaine domaine;

    @OneToMany(mappedBy = "OffreStage",cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"description","datePublication","dateLimite","candidatures","testTechniques"})
    private Set<Candidature> candidatures;

    @OneToOne
    private Quiz Quiz;
}
