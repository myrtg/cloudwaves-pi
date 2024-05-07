package tn.pfeconnect.pfeconnect.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.pfeconnect.pfeconnect.entities.Evenement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface EvenementsService {
    Evenement addEvenement(Evenement evenement);
    Evenement updateEvenement(Evenement evenement);

    void  removeEvenements(Long IdEvenement);
    Evenement retrieveEvenement(Long IdEvenement);
    List<Evenement> retrieveEvenements();

    void generatePdf(HttpServletResponse response);
    void generateCSV(HttpServletResponse response);
    List<Evenement> retrieveEvenementOrderByNbPlace();
    int getDuree(Long idEvenement);


    static byte[] generateQRCode(Evenement evenement, int width, int height)
    {
        Logger logger = LoggerFactory.getLogger(EvenementsService.class);
        try {
            String content = evenement.toString();
            System.out.println(content);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (WriterException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }
}
