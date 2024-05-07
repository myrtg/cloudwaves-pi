package tn.pfeconnect.pfeconnect.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.pfeconnect.pfeconnect.entities.Projet;
import tn.pfeconnect.pfeconnect.entities.SousCategorie;
import tn.pfeconnect.pfeconnect.services.ProjetService;
import tn.pfeconnect.pfeconnect.services.SousCategoryService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/projet")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4300")
public class ProjectController {
    private final ProjetService projetService;
    private final SousCategoryService subCategorieService;

    @PostMapping("/add")
    public Projet addProjet(@RequestParam("file") MultipartFile file,
                            @RequestPart("projet") String projetJson) {
        try {
            ProjetRequest projetRequest = new ObjectMapper().readValue(projetJson, ProjetRequest.class);

            Projet projet = new Projet();
            projet.setDescription(projetRequest.getDescription());
            projet.setTitre(projetRequest.getTitre());

            // Read the bytes from the uploaded file and set them as the project report
            projet.setFichierRapport(file.getBytes());
            System.out.println("File uploaded: " + file);
            System.out.println("File uploaded: " + Arrays.toString(file.getBytes()));


            // Set the subcategory
            SousCategorie subCategorie = subCategorieService.retreiveAllSousCategorie().stream()
                    .filter(sousCategorie -> Objects.equals(sousCategorie.getIdSousCategorie(), projetRequest.getSubCategoryId()))
                    .findFirst()
                    .orElse(null);
            if (subCategorie == null) {
                throw new IllegalArgumentException("No subCategory found with the provided subCategoryId: " + projetRequest.getSubCategoryId());
            }
            projet.setSousCategorie(subCategorie);

            return projetService.addProjet(projet);
        } catch (IOException e) {
            // Handle the exception appropriately
            throw new RuntimeException("Failed to process the file", e);
        }
    }


    @GetMapping
    public List<Projet> getAllProjet() {
        return projetService.retrieveAllProjet();
    }

    @PutMapping("/update/{id}")
    public Projet updateProject(@PathVariable long id, @RequestBody Projet projet) {
        return projetService.updateProjet(id, projet);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeProjet(@PathVariable long id) {
        return projetService.removeProjet(id);
    }

    //add function to file upload
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return projetService.uploadFile(file);
    }

    @GetMapping("/download/{projectId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long projectId) {
        System.out.println("File downloaded: " + projectId);
        // Retrieve the file data from the database based on the project ID
        Projet projet = projetService.retrieveAllProjet().stream()
                .filter(p -> Objects.equals(p.getIdProjet(), projectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No project found with the provided projectId: " + projectId));

        // Set the file data as a ByteArrayResource
        ByteArrayResource resource = new ByteArrayResource(projet.getFichierRapport());
        System.out.println("File downloaded: " + Arrays.toString(projet.getFichierRapport()));

        // Return the file data with appropriate headers for download
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + projet.getTitre() + ".txt")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}