package tn.pfeconnect.pfeconnect.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetRequest {
    private String titre;
    private String description;
    private String fichierRapport;
    private Long subCategoryId;
}