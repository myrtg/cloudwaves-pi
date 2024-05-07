package tn.pfeconnect.pfeconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.entities.OffreStage;
import tn.pfeconnect.pfeconnect.servicesImpl.OffreStageServiceImpl;

import java.util.List;

@RestController
@RequestMapping("OffreStage")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class OffreStageController {
    @Autowired
    OffreStageServiceImpl offreStageService;

    @GetMapping("allOffers")
    public ResponseEntity<List<OffreStage>> getAllOffreStage() {
        return offreStageService.getAllOffers();
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody OffreStage offreStage) {
        return offreStageService.addOffers(offreStage);
    }

    @DeleteMapping("delete/{id}")
    public void remove(@PathVariable long id) {
        offreStageService.deleteById(id);
    }

}