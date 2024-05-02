package com.projectpi.cloudwaves.Services;

import com.projectpi.cloudwaves.Repository.QuestionRepository;
import com.projectpi.cloudwaves.entites.Domaine;
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
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestions(Questions question) {
        try {
            questionRepository.save(question);
            return new ResponseEntity<>("succes", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", BAD_REQUEST);
    }

}
