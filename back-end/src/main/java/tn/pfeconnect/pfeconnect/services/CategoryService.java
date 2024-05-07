package tn.pfeconnect.pfeconnect.services;

import tn.pfeconnect.pfeconnect.entities.Categorie;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Categorie> retriveAllCategorie();
    Categorie addCategorie(Categorie categorie);
    Categorie updateCategorie(Long idCategorie, Categorie categorie);
    Map<String, String> removeCategorie(Long idCategorie);

}
