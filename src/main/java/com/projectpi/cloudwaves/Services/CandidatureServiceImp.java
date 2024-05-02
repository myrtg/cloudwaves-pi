package com.projectpi.cloudwaves.Services;
import com.projectpi.cloudwaves.entites.Questions;
import com.projectpi.cloudwaves.entites.Responses;

import com.projectpi.cloudwaves.Repository.CandidatureRepository;
import com.projectpi.cloudwaves.Repository.QuizRepository;
import com.projectpi.cloudwaves.entites.Candidature;
import com.projectpi.cloudwaves.entites.Quiz;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CandidatureServiceImp implements ICandidatureService {

    CandidatureRepository candidatureRepository;
    QuizRepository quizRepository;
    QuizService quizService;
    QuestionService questionService;
    FileSystemStorageService fileSystemStorageService;
    private final String fileUploadPath = "src\\main\\static\\pdf"; // Define file upload path


    public Candidature createCandidate(String name, String email, String phoneNumber, MultipartFile cv) throws IOException {
        // First, store the CV file
        if (cv.isEmpty()) {
            throw new IllegalArgumentException("CV file is empty");
        }

        // Get the file extension
        String fileExtension = StringUtils.getFilenameExtension(cv.getOriginalFilename());
        // Check if the file extension is valid (PDF or DOC)
        if (!"pdf".equalsIgnoreCase(fileExtension) && !"doc".equalsIgnoreCase(fileExtension)) {
            throw new IllegalArgumentException("CV file must be a PDF or DOC document");
        }
// Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Save the file to the upload path
        Path filePath = Paths.get(fileUploadPath, fileName);
        Files.copy(cv.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        Candidature candidature = new Candidature();
        candidature.setName(name);
        candidature.setEmail(email);
        candidature.setPhoneNumber(phoneNumber);
        candidature.setCvName(cv.getOriginalFilename());
        candidature.setCv(filePath.toString()); //
        // Return the stored file name


        return candidatureRepository.save(candidature);
    }

    @Override
    public List<Candidature> findByIdUser(long id) {
        return candidatureRepository.findByUserId(id);
    }


    @Override
    public Candidature addCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public Candidature updateCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public void removeCandidature(Long idCandidature) {
        candidatureRepository.deleteById(idCandidature);
    }

    @Override
    public Candidature retrieveCandidature(Long idCandidature) {
        return candidatureRepository.findById(idCandidature).get();
    }

    @Override
    public List<Candidature> retrieveCandidature() {
        return candidatureRepository.findAll();
    }


}
