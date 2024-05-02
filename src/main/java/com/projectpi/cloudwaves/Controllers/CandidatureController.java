package com.projectpi.cloudwaves.Controllers;

import com.projectpi.cloudwaves.Repository.CandidatureRepository;
import com.projectpi.cloudwaves.Services.ICandidatureService;
import com.projectpi.cloudwaves.entites.Candidature;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/candidature")
public class CandidatureController {
    ICandidatureService candidatureService;
    CandidatureRepository candidatureRepository;

    @GetMapping("/retrieve-all-candidature")
    public List<Candidature> getAllCandidature() {
        return candidatureService.retrieveCandidature();
    }

    @PutMapping("update-candidature")
    public Candidature updateCandidature(@RequestBody Candidature candidature) {
        return candidatureService.updateCandidature(candidature);
    }

    @DeleteMapping("/delete-candidature/{id-candidature}")
    public void removeCandidature(@PathVariable("id-candidature") long idCandidature) {
        candidatureService.removeCandidature(idCandidature);
    }

    @PostMapping("/create")
    public ResponseEntity<Candidature> createCandidate(@RequestParam String name,
                                                       @RequestParam String email,
                                                       @RequestParam String phoneNumber,
                                                       @RequestParam("cv") MultipartFile cv) {
        try {
            Candidature candidate = candidatureService.createCandidate(name, email, phoneNumber, cv);
            return ResponseEntity.ok(candidate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/find/{id}")
    public List<Candidature> findById(@PathVariable long id) {
        return candidatureService.findByIdUser(id);
    }
    @GetMapping("/{id}/cv")
    public ResponseEntity<Resource> getCvById(@PathVariable long id) {
        Candidature candidature = candidatureService.retrieveCandidature(id);
        if (candidature == null || candidature.getCv() == null) {
            return ResponseEntity.notFound().build();
        }

        // Read the CV file from the file system
        File cvFile = new File(candidature.getCv());
        if (!cvFile.exists()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        try {
            // Create InputStreamResource from the CV file
            InputStreamResource resource = new InputStreamResource(new FileInputStream(cvFile));

            // Create response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Assuming PDF format
            headers.setContentDispositionFormData("attachment", candidature.getCvName());

            // Return the CV file as a ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
