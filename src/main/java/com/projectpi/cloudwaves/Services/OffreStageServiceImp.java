package com.projectpi.cloudwaves.Services;

import com.projectpi.cloudwaves.Repository.OffreStageRepository;
import com.projectpi.cloudwaves.Repository.QuestionRepository;
import com.projectpi.cloudwaves.entites.OffreStage;
import com.projectpi.cloudwaves.entites.Questions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class OffreStageServiceImp {
    @Autowired
    OffreStageRepository offreStageRepository;

    public ResponseEntity<List<OffreStage>> getAllOffers() {
        try {
            return new ResponseEntity<>(offreStageRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , BAD_REQUEST);
    }
    public ResponseEntity<String> addOffers(OffreStage offreStage) {
        try {
            offreStageRepository.save(offreStage);
            return new ResponseEntity<>("succes", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", BAD_REQUEST);
    }

    public void deleteById(long id) {
        offreStageRepository.deleteById(id);
    }

}
