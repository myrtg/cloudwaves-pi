package com.cloudwaves.pfeconnect.controllers;

import com.cloudwaves.pfeconnect.entities.Inscription;
import com.cloudwaves.pfeconnect.services.InscriptionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("api/inscriptions")
public class InscriptionController {
    InscriptionService inscriptionService;

    @GetMapping("/getAll")
    public List<Inscription> getAllInscriptions()
    {
        return inscriptionService.retrieveInscriptions();
    }

    @GetMapping("/getById")
    public Inscription getInscription(@RequestBody Long idInscription)
    {
        return inscriptionService.retrieveInscription(idInscription);
    }
    @PostMapping("/add")
    public Inscription addInscription(@RequestBody Inscription inscription)
    {
        return inscriptionService.addInscription(inscription);
    }
    @PutMapping("/update")
    public Inscription updateInscription(@RequestBody Inscription inscription)
    {
        return inscriptionService.updateInscription(inscription);
    }
    @DeleteMapping("/delete")
    public void deleteInscription(@RequestBody Long idInscription)
    {
        inscriptionService.removeInscription(idInscription);
    }
}
