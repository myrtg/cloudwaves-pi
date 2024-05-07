package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idProjet;
    String titre;
    String description;
    @Lob
    private byte[] fichierRapport;

    @ManyToOne
    @JoinColumn(name = "sousCategorie_id")
    @JsonIgnore
    private SousCategorie sousCategorie;
}
