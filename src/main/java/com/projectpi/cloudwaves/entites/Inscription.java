package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Inscription")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription")
    private long id;
    private long id_user;

    @ManyToOne
    private Evenement evenement;


}
