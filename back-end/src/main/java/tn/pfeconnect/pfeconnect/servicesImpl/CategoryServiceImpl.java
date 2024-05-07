package tn.pfeconnect.pfeconnect.servicesImpl;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.entities.Categorie;
import tn.pfeconnect.pfeconnect.repositories.CategoryRepository;
import tn.pfeconnect.pfeconnect.services.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categorieRepository;

    @Override
    public List<Categorie> retriveAllCategorie() {
        return categorieRepository.findAll();

    }

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Long idCategorie, Categorie categorie) {
        return categorieRepository.findById(idCategorie)
                .map(p -> {
                    p.setNomCategorie(categorie.getNomCategorie());
                    return categorieRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Categorie non trouvée"));

    }

    @Override
    public Map<String, String> removeCategorie(Long idCategorie) {
        categorieRepository.deleteById(idCategorie);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Catégorie supprimé");
        return response;
    }
}
