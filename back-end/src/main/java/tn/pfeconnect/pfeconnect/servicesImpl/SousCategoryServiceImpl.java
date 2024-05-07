package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.entities.SousCategorie;
import tn.pfeconnect.pfeconnect.repositories.SousCategoryRepository;
import tn.pfeconnect.pfeconnect.services.SousCategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SousCategoryServiceImpl implements SousCategoryService {
    private final SousCategoryRepository sousCategorieRepository;
    @Override
    public List<SousCategorie> retreiveAllSousCategorie() {
        return sousCategorieRepository.findAll();
    }

    @Override
    public SousCategorie addSousCategorie(SousCategorie sousCategorie) {
        return sousCategorieRepository.save(sousCategorie);
    }

    @Override
    public SousCategorie updateSousCategorie(Long idSousCategorie, SousCategorie sousCategorie) {
        return sousCategorieRepository.findById(idSousCategorie)
                .map(p-> {
                    p.setNomSousCategorie(sousCategorie.getNomSousCategorie());

                    return sousCategorieRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Sous categorie n'existe pas"));

    }

    @Override
    public Map<String, String> removeSousCategorie(Long idSousCategorie) {
        sousCategorieRepository.deleteById(idSousCategorie);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Sous Catégorie supprimé");
        return response;
    }
}