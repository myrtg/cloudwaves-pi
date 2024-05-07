package tn.pfeconnect.pfeconnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.pfeconnect.pfeconnect.enums.Domaine;

import java.time.LocalDate;
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
    private long idOffre;
    private String titre;
    private String description;
    private LocalDate datePublication;
    private LocalDate dateLimite;
    @Enumerated(EnumType.STRING)
    private Domaine domaine;

    @OneToMany(mappedBy = "OffreStage",cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"description","datePublication","dateLimite","candidatures","testTechniques"})
    private Set<Candidature> candidatures;

    @OneToOne
    private Quiz Quiz;
}
