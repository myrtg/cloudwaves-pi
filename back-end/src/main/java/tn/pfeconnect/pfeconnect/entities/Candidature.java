package tn.pfeconnect.pfeconnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="candidature")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_candidature")
    private long idCandidature;
    private String name;
    private String email;
    private String PhoneNumber;
    private String cv;
    private String cvName;
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties({"email", "motdepasse", "role","evenements","candidatures"})
    private User Utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_offre")
    @JsonIgnoreProperties({"description","datePublication","dateLimite","candidatures","testTechniques"})
    private OffreStage OffreStage;
}
