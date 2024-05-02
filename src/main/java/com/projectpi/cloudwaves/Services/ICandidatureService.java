package com.projectpi.cloudwaves.Services;

import com.projectpi.cloudwaves.entites.Candidature;
import com.projectpi.cloudwaves.entites.Responses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface ICandidatureService {
    Candidature addCandidature (Candidature candidature);
    Candidature updateCandidature (Candidature candidature);
    void removeCandidature (Long idCandidature);
    Candidature retrieveCandidature (Long idCandidature);
    List<Candidature> retrieveCandidature();

    Candidature createCandidate(String name, String email, String phoneNumber, MultipartFile cv) throws IOException;
    List<Candidature> findByIdUser(long id);

}
