package com.cloudwaves.pfeconnect.controllers;

import com.cloudwaves.pfeconnect.entities.Evenement;
import com.cloudwaves.pfeconnect.services.EvenementsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)

@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("api/evenements")
public class EvenementsController {
    EvenementsService evenementsService;
    @GetMapping("/getAll")
    public List<Evenement> getAllEvenements()
    {
        return evenementsService.retrieveEvenements();
    }
    @GetMapping("/getById")
    public Evenement getEvenement(@RequestBody Long idEvenement)
    {
        return evenementsService.retrieveEvenement(idEvenement);
    }
    @GetMapping("/getPdf")
    public void getPdf(HttpServletResponse response)
    {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=events_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        evenementsService.generatePdf(response);
    }
    @GetMapping("/getCsv")
    public void getCsv(HttpServletResponse response)
    {
        evenementsService.generateCSV(response);
    }
    @GetMapping("/getOrderByNbPlaces")
    public List<Evenement> getOrderByNbPlace()
    {
        return evenementsService.retrieveEvenementOrderByNbPlace();
    }
    @GetMapping("/getDuree")
    public int getDuree(@RequestParam(name = "id") Long idEvenement)
    {
        return evenementsService.getDuree(idEvenement);
    }
    @PostMapping("/add")
    public Evenement addEvenements(@RequestParam("image")MultipartFile imageFile,@RequestParam("name")String nom,@RequestParam("titre")String titre,
                                   @RequestParam("description")String description,@RequestParam("date_debut")String dateDebut,
                                   @RequestParam("date_fin")String dateFin,@RequestParam("nb_places")int nbPlaces,@RequestParam("tutor")String tutor)
    {
        try {
            Evenement evenement = new Evenement();

            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            Date date_debut = df.parse(dateDebut);
            Date date_fin = df.parse(dateFin);

            if (!imageFile.isEmpty()) {
                // Convert MultipartFile to byte[]
                byte[] imageBytes = imageFile.getBytes();
                // Set the byte[] to the Evenement object
                evenement.setImage(imageBytes);
                evenement.setTitre(titre);
                evenement.setNom(nom);
                evenement.setDescription(description);
                evenement.setDateDebut(date_debut);
                evenement.setDateFin(date_fin);
                evenement.setNbPlace(nbPlaces);
                evenement.setTutor(tutor);
            }
            return evenementsService.addEvenement(evenement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping("/update")
    public Evenement updateEvenement(@RequestBody Evenement evenement)
    {
        return evenementsService.updateEvenement(evenement);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEvenement(@PathVariable(name="id") Long idEvenement)
    {
        evenementsService.removeEvenements(idEvenement);
    }
    @GetMapping("/generateQRCode")
    public void generateQRCode(@RequestParam(name = "id") Long id, HttpServletResponse response) throws IOException {
        Evenement evenement =  evenementsService.retrieveEvenement(id);
        response.setContentType("image/png");
        byte[] qrCode = EvenementsService.generateQRCode(evenement, 500, 500);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrCode);
    }
}
