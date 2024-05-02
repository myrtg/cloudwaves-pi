package com.projectpi.cloudwaves.entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.sql.Blob;

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
    private Utilisateur Utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_offre")
    @JsonIgnoreProperties({"description","datePublication","dateLimite","candidatures","testTechniques"})
    private OffreStage OffreStage;
}
