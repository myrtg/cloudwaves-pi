package tn.pfeconnect.pfeconnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Evenement")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evenement")
    private long id;
    private String titre;
    public String nom;
    private String description;
    private Date DateDebut;
    private Date DateFin;
    private int nbPlace;
    private String tutor;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    byte[] image;

    @ManyToOne
    @JsonIgnore
    private User utilisateur;

    @OneToMany(mappedBy = "evenement",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Inscription> inscriptionSet;

    @Override
    public String toString(){
        String s = "";
        return s + "Titre :"+ titre + "\n"+ "Description :"+description +"\n"+ "Nombre Place :"+nbPlace;
    }
}
