package tn.pfeconnect.pfeconnect.services;

import org.springframework.web.multipart.MultipartFile;
import tn.pfeconnect.pfeconnect.entities.Projet;

import java.util.List;
import java.util.Map;

public interface ProjetService {
    List<Projet> retrieveAllProjet();
    Projet addProjet(Projet projet);
    Projet updateProjet(Long idProject,Projet projet);
    Map<String, String> removeProjet(Long idProject);
    String uploadFile(MultipartFile file);
}
