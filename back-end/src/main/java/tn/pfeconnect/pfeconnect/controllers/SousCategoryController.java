package tn.pfeconnect.pfeconnect.controllers;


import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.entities.Categorie;
import tn.pfeconnect.pfeconnect.entities.SousCategorie;
import tn.pfeconnect.pfeconnect.services.CategoryService;
import tn.pfeconnect.pfeconnect.services.SousCategoryService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/souscategorie")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4300")
public class SousCategoryController {
    private final SousCategoryService sousCategorieService;
    private final CategoryService categoryService;

    @PostMapping("/add")
    public SousCategorie addSousCategorie(@RequestBody SousCategoryRequest sousCategorieRequest) {
        // Logging
        System.out.println("Processing request to add a subcategory...");
        System.out.println("Request: " + sousCategorieRequest.getCategoryId());
        System.out.println("Request: " + sousCategorieRequest.getNomSousCategorie());

        // Create a new SousCategorie object and set its category using the provided categoryId
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.setNomSousCategorie(sousCategorieRequest.getNomSousCategorie());

        // Retrieve the Category from the database using the categoryId
        List<Categorie> matchingCategories = categoryService.retriveAllCategorie().stream().filter(categorie ->
                        Objects.equals(categorie.getIdCategorie(), sousCategorieRequest.getCategoryId()))
                .collect(Collectors.toList());

        System.out.println("Matching categories: " + matchingCategories);

        // Check if any matching category was found
        if (!matchingCategories.isEmpty()) {
            // Set the first matching category found
            sousCategorie.setCategorie(matchingCategories.get(0));
        } else {
            // Handle the case where no matching category was found (e.g., throw an exception or return an error response)
            // For example:
            System.out.println("No category found with the provided categoryId: " + sousCategorieRequest.getCategoryId());
            throw new IllegalArgumentException("No category found with the provided categoryId: " + sousCategorieRequest.getCategoryId());
        }

        // Logging
        System.out.println("Adding subcategory: " + sousCategorie);

        return sousCategorieService.addSousCategorie(sousCategorie);
    }


    @GetMapping
    public List<SousCategorie> getAllSousCategories() {
        return sousCategorieService.retreiveAllSousCategorie();
    }

    @PutMapping("/updatesouscategorie/{id}")
    public SousCategorie updateSousCategorie(@PathVariable long id, @RequestBody SousCategorie sousCategorie) {
        return sousCategorieService.updateSousCategorie(id, sousCategorie);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeSousCategorie(@PathVariable long id) {
        return sousCategorieService.removeSousCategorie(id);
    }
}