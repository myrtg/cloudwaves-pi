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
    public Evenement addEvenements(@RequestBody Evenement evenement)
    {
        return evenementsService.addEvenement(evenement);
    }


    @PutMapping("/update")
    public Evenement updateEvenement(@RequestBody Evenement evenement)
    {
        return evenementsService.updateEvenement(evenement);
    }
    @DeleteMapping("/delete")
    public void deleteEvenement(@RequestBody Long idEvenement)
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
