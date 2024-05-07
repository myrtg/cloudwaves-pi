package tn.pfeconnect.pfeconnect.services;

import org.springframework.web.multipart.MultipartFile;
import tn.pfeconnect.pfeconnect.entities.Candidature;

import java.io.IOException;
import java.util.List;

public interface CandidatureService {
    Candidature addCandidature (Candidature candidature);
    Candidature updateCandidature (Candidature candidature);
    void removeCandidature (Long idCandidature);
    Candidature retrieveCandidature (Long idCandidature);
    List<Candidature> retrieveCandidature();

    Candidature createCandidate(String name, String email, String phoneNumber, MultipartFile cv) throws IOException;
    List<Candidature> findByIdUser(long id);

}