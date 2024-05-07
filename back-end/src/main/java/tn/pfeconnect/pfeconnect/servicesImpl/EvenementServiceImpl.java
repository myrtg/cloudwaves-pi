package tn.pfeconnect.pfeconnect.servicesImpl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import tn.pfeconnect.pfeconnect.entities.Evenement;
import tn.pfeconnect.pfeconnect.repositories.EvenementsRepository;
import tn.pfeconnect.pfeconnect.services.EvenementsService;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EvenementServiceImpl implements EvenementsService {
    EvenementsRepository evenementsRepository;
    @Override
    public Evenement addEvenement(Evenement evenement) {
        return evenementsRepository.save(evenement);
    }

    @Override
    public Evenement updateEvenement(Evenement evenement) {
        return evenementsRepository.save(evenement);
    }

    @Override
    public void removeEvenements(Long IdEvenement) {
        Evenement evenement = evenementsRepository.findById(IdEvenement).get();
        evenementsRepository.delete(evenement);

    }

    @Override
    public Evenement retrieveEvenement(Long IdEvenement) {
        return  evenementsRepository.findById(IdEvenement).get();
    }


    @Override
    public List<Evenement> retrieveEvenements() {
        return evenementsRepository.findAll();
    }

    @Override
    public void generatePdf(HttpServletResponse response) {
        PdfPTable table = new PdfPTable(6);
        List<Evenement> listeEvenements = evenementsRepository.findAll();
        // Write Header
        PdfPCell Cell = new PdfPCell();
        Cell.setBackgroundColor(Color.BLUE);
        Cell.setPadding(1);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.white);
        Cell.setPhrase(new Phrase("Event ID",font));
        table.addCell(Cell);
        Cell.setPhrase(new Phrase("Date Debut",font));
        table.addCell(Cell);
        Cell.setPhrase(new Phrase("Date Fin",font));
        table.addCell(Cell);
        Cell.setPhrase(new Phrase("Description",font));
        table.addCell(Cell);
        Cell.setPhrase(new Phrase("Nb Places",font));
        table.addCell(Cell);
        Cell.setPhrase(new Phrase("Titre",font));
        table.addCell(Cell);
        //Cell.setPhrase(new Phrase("Id Admin",font));
        //table.addCell(Cell);

        // Fill the table
        for (Evenement evenement: listeEvenements)
        {
            table.addCell(String.valueOf(evenement.getId()));
            table.addCell(String.valueOf(evenement.getDateDebut()));
            table.addCell(String.valueOf(evenement.getDateFin()));
            table.addCell(String.valueOf(evenement.getDescription()));
            table.addCell(String.valueOf(evenement.getNbPlace()));
            table.addCell(String.valueOf(evenement.getTitre()));
            //table.addCell(String.valueOf(evenement.getUtilisateur().getId()));
        }

        //  Export The table
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document,response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.open();
        font.setSize(10);
        Paragraph p = new Paragraph("List of Event");
        p.setAlignment(Paragraph.ALIGN_CENTER);
        //table.setWidths(new float[] {1.5f,3.5f,3.0f,3.0f,1.5f});
        table.setSpacingBefore(10);
        document.add(table);
        document.close();
    }

    @Override
    public void generateCSV(HttpServletResponse response) {
        List<Evenement> evenements = evenementsRepository.findAll();
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=events_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        try {
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"Event ID", "Date Debut", "Date Fin", "Description", "Nombre Places","Titre"};
            String[] nameMapping = {"id", "DateDebut", "DateFin", "description", "nbPlace","titre"};
            csvWriter.writeHeader(csvHeader);
            for (Evenement evenement: evenements)
            {
                csvWriter.write(evenement,nameMapping);
            }
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Evenement> retrieveEvenementOrderByNbPlace() {
        return evenementsRepository.retrieveEventsOrderByNbplace();
    }

    @Override
    public int getDuree(Long idEvenement) {
        Evenement event = evenementsRepository.findById(idEvenement).get();
        LocalDate dateDebut = event.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateFin = event.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(dateDebut, dateFin);

        return period.getDays();
    }


}
