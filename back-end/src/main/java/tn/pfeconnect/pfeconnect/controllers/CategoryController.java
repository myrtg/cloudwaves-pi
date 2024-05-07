package tn.pfeconnect.pfeconnect.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.entities.Categorie;
import tn.pfeconnect.pfeconnect.services.CategoryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorie")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4300")
public class CategoryController {
    private final CategoryService categorieService;

    @PostMapping("/add")
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return categorieService.addCategorie(categorie);
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.retriveAllCategorie();
    }

    @PutMapping("/updatecategorie/{id}")
    public Categorie updateCategorie(@PathVariable long id, @RequestBody Categorie categorie) {
        return categorieService.updateCategorie(id, categorie);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeCategorie(@PathVariable long id) {
        return categorieService.removeCategorie(id);
    }
}
