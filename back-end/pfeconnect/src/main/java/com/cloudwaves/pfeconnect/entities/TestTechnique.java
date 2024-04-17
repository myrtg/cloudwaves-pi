package com.cloudwaves.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "TestTechnique")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class TestTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_test_technique")
    private long id;
    private long timeTotal;

    @ManyToMany
    private Set<OffreStage> offreStages;

}
