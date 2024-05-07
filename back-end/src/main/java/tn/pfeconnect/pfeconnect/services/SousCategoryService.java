package tn.pfeconnect.pfeconnect.services;

import tn.pfeconnect.pfeconnect.entities.SousCategorie;

import java.util.List;
import java.util.Map;

public interface SousCategoryService {
    List<SousCategorie> retreiveAllSousCategorie();
    SousCategorie addSousCategorie(SousCategorie sousCategorie);
    SousCategorie updateSousCategorie(Long idSousCategorie, SousCategorie sousCategorie);
    Map<String, String> removeSousCategorie(Long idSousCategorie);
}
