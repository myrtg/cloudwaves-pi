package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "idProjet",nullable = false)
    private Projet projet;
}