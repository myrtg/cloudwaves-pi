package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.pfeconnect.pfeconnect.entities.Projet;
import tn.pfeconnect.pfeconnect.repositories.ProjectRepository;
import tn.pfeconnect.pfeconnect.services.NotificationService;
import tn.pfeconnect.pfeconnect.services.ProjetService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProjetServiceImpl implements ProjetService {
    private final ProjectRepository projectRepository;
    private final NotificationService notificationService;

    @Override
    public List<Projet> retrieveAllProjet() {
        List<Projet> projets = projectRepository.findAll();
        notificationService.sendNotification("Liste des projet récupérée");
        return projets;
    }

    @Override
    public Projet addProjet(Projet projet) {
        Projet newProjet = projectRepository.save(projet);
        notificationService.sendNotification("Nouveau projet ajouté :" + projet.getTitre());
        return newProjet;
    }

    @Override
    public Projet updateProjet(Long idProject, Projet projet) {
        return projectRepository.findById(idProject)
                .map(p -> {
                    p.setTitre(projet.getTitre());
                    p.setDescription(projet.getDescription());
                    p.setFichierRapport(projet.getFichierRapport());
                    Projet updatedProjet = projectRepository.save(p);
                    notificationService.sendNotification("Projet mis à jour :" + updatedProjet.getTitre());
                    return updatedProjet;
                }).orElseThrow(() -> new RuntimeException("Projet non trouvé!"));

    }

    @Override
    public Map<String, String> removeProjet(Long idProject) {
        projectRepository.deleteById(idProject);
        //notificationService.sendNotification("Projet supprimé");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Projet supprimé");
        return response;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Fichier vide");
        } else {
            return file.getOriginalFilename();
        }
    }
}